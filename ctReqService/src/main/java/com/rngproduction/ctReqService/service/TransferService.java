package com.rngproduction.ctReqService.service;

import com.rngproduction.ctReqService.models.CT;
import com.rngproduction.ctReqService.repository.CTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    CTRepository ctRepository;

    @Transactional
    public void saveIfNotExists(CT ct) {
        if (!ctRepository.existsById(ct.getId())) {
            ctRepository.save(ct);
        }
    }

    @Transactional
    public boolean anyExist() {
        return ctRepository.anyExist();
    }

    /**
     * После первого запуска считаем все ct уже отправленными,
     * меняем статус всех ct на Не требует отправки (needSend = 0)
     */
    @Transactional
    public void firstUpdate() {
        ctRepository.firstUpdate();
    }

    @Transactional
    public List<CT> getListNeedSend() {
        return ctRepository.findListNeedSend();
    }

    @Transactional
    public void updateNeedSend(String ctId) {
        ctRepository.setNeedSendFalse(ctId);
    }
}
