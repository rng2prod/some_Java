package com.rngproduction.ctReqService.service;

import com.rngproduction.ctReqService.config.Property;
import com.rngproduction.ctReqService.models.CT;
import com.rngproduction.ctReqService.models.CTList;
import com.rngproduction.ctReqService.utils.FileUtil;
import com.rngproduction.ctReqService.utils.MailUtil;
import com.rngproduction.ctReqService.utils.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@PropertySource(value = "classpath:system.properties", encoding = "UTF-8")
public class SchedulerService {

    @Autowired
    Property property;
    @Autowired
    RestUtil restUtil;
    @Autowired
    TransferService transferService;
    @Autowired
    FileUtil fileUtil;
    @Autowired
    MailUtil mailUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class);

    {
        LOGGER.info("Запуск сервиса запросов и периодической отправки списка CT");
    }

    @Scheduled(fixedDelayString = "${intervalCTReqService}")
    public void SchedulerServiceTask() {
        String url = property.getUrlCT();
        String request = property.getRequestCTService();
        String fileCatalog = property.getFileCatalogPath();

        try {
            LOGGER.debug("Отправляем запрос списка CT на адрес " + url);
            CTList ctList = restUtil.sendRequest(url, request, CTList.class);

            if (ctList.getCTList().isEmpty()) {
                LOGGER.info("Получен пустой список CT");
                return;
            }

            boolean anyExist = transferService.anyExist();
            ctList.getCTList().forEach(T -> transferService.saveIfNotExists(T));
            if (!anyExist) {
                transferService.firstUpdate();
            }

            List<CT> ctToSend = transferService.getListNeedSend();
            if (ctToSend.isEmpty()) {
                LOGGER.info("Не найдены CT, разрешенные для отправки");
                return;
            }

            LOGGER.info("Найдены CT для отправки, количество: " + ctToSend.size());
            ctToSend.forEach(ct -> {
                try {
                    String[] file = fileUtil.createFile(ct, fileCatalog, "txt");
                    if (property.getMailReceivers() == null || property.getMailReceivers().isEmpty()) {
                        LOGGER.error("Список получателей пуст, письмо не будет отправлено");
                    } else {
                        LOGGER.info("Отправляем письмо на адреса: " + property.getMailReceivers());
                        mailUtil.sendMail(property.getMailSender(),
                                property.getMailReceivers(),
                                property.getMailSubject(),
                                file[0],
                                file[1]);
                    }

                    transferService.updateNeedSend(ct.getId());
                } catch (IOException e) {
                    LOGGER.error("Ошибка при создании файлов CT");
                    LOGGER.error(e.getMessage());
                }
            });

        } catch (Exception e) {
            LOGGER.error("Необработанная ошибка запроса списка CT");
            LOGGER.error(e.getMessage());
        }
    }
}
