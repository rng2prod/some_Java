package com.rngproduction.ctReqService.utils;

import com.rngproduction.ctReqService.models.CT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    private static final Charset CODE866 = Charset.forName("cp866");

    public String[] createFile(CT ct, String path, String type) throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmm");
        Date date = Calendar.getInstance().getTime();
        String dateStr = formatter.format(date);
        String payerName = ct.getPayerNameInt().replace(" ", "_");
        String fileName = dateStr + "_" +
                ((payerName.length() < 17) ? payerName : payerName.substring(0, 15)) +
                "_" + ct.getId() + "." + type;
        String filePath = path + fileName;
        OutputStream os = Files.newOutputStream(Paths.get(filePath));

        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, CODE866))) {
            pw.println("/* hidden string */");
            pw.println("/* hidden string */");

            DateFormat formatForDoc = new SimpleDateFormat("yyMMdd");
            String dateForDoc = formatForDoc.format(date);

            pw.println("/* hidden string */" + dateForDoc);
            pw.println("/* hidden string */");
            pw.println("/* hidden string */");
            pw.println("/* hidden string */");
            pw.println("/* hidden string */" +
                    formatForDoc.format(ct.getPayUntilDate()) +
                    ct.getCtIsoCode() +
                    ct.getAmountTrans().replace(".", ","));
            pw.println("/* hidden string */" + ct.getPayerAccount());

            List<String> field50 = getListForField50(ct);
            for (String s : field50) {
                pw.println(s.toUpperCase());
            }
            pw.println("/* hidden string */");

            if (!(ct.getImediaBankSwift().equals("") || ct.getImediaBankSwift() == null)) {
                pw.println("/* hidden string */" + ct.getImediaBankSwift());
            }
            pw.println("/* hidden string */" + ct.getInstBankSwift());
            pw.println("/* hidden string */" + ct.getInstAccount().replaceAll("[^a-zA-Z0-9]", ""));

            List<String> field59 = getListForField59(ct);
            for (String s : field59) {
                pw.println(s.toUpperCase());
            }
            pw.print("/* hidden string */");

            List<String> field70 = getListForField70(ct);
            for (String s : field70) {
                pw.println(s.toUpperCase());
            }
            if (field70.isEmpty()) {
                pw.println("");
            }
            pw.println("/* hidden string */" + ct.getChargesType());

            if (!(ct.getImediaInfo().equals("") || ct.getImediaInfo() == null)) {
                pw.print("/* hidden string */");
                List<String> field72 = getListForField72(ct);
                for (String s : field72) {
                    pw.println(s);
                }
                if (field72.isEmpty()) {
                    pw.println("");
                }
            }
            pw.print("/* hidden string */");
        }
        os.close();
        LOGGER.info("запись файла  " + filePath);

        return new String[]{fileName, filePath};
    }

    private List<String> getListForField50(CT ct) {

        List<String> result = new ArrayList<>();
        int counter = 0;

        List<String> nameList = getListFromString(ct.getPayerNameInt(), " ", 34, 2);
        counter = counter + nameList.size();
        nameList.forEach(name -> {
            if (name.length() > 34) {
                result.add(name + "/* hidden string */");
            } else {
                result.add(name);
            }
        });

        result.add("/* hidden string */" + ct.getPayerInn());
        counter++;

        List<String> addressList = getListFromString(ct.getPayerAddress(), " ", 34, 4 - counter);
        for (String adr : addressList) {
            counter++;
            String tmp = adr;
            if (counter == 4) {
                tmp = adr + ", " + ct.getPayerPlace() + ", " + ct.getPayerCountryCode();
            }
            if (tmp.length() > 34) {
                result.add(tmp + "/* hidden string */");
            } else {
                result.add(tmp);
            }
        }
        if (counter < 4) {
            String tmp = ct.getPayerPlace() + ", " + ct.getPayerCountryCode();
            if (tmp.length() > 34) {
                result.add(tmp + "/* hidden string */");
            } else {
                result.add(tmp);
            }
        }
        return result;
    }

    private List<String> getListForField59(CT ct) {

        List<String> result = new ArrayList<>();
        int counter = 0;

        List<String> nameList = getListFromString(ct.getInstName(), " ", 34, 3);
        counter = counter + nameList.size();
        nameList.forEach(name -> {
            if (name.length() > 34) {
                result.add(name + "/* hidden string */");
            } else {
                result.add(name);
            }
        });

        List<String> addressList = getListFromString(ct.getInstAddress(), " ", 34, 4 - counter);
        for (String adr : addressList) {
            counter++;
            String tmp = adr;
            if (counter == 4) {
                tmp = adr + ", " + ct.getInstPlace() + ", " + ct.getInstCountryCode();
            }
            if (tmp.length() > 34) {
                result.add(tmp + "/* hidden string */");
            } else {
                result.add(tmp);
            }
        }
        if (counter < 4) {
            String tmp = ct.getInstPlace() + ", " + ct.getInstCountryCode();
            if (tmp.length() > 34) {
                result.add(tmp + "/* hidden string */");
            } else {
                result.add(tmp);
            }
        }
        return result;
    }

    private List<String> getListForField70(CT ct) {

        List<String> result = new ArrayList<>();
        List<String> details = getListFromString(ct.getPaymentDetails(), " ", 34, 4);
        details.forEach(d -> {
            if (d.length() > 34) {
                result.add(d + "/* hidden string */");
            } else {
                result.add(d);
            }
        });
        return result;
    }

    private List<String> getListForField72(CT ct) {

        List<String> result = new ArrayList<>();
        List<String> details = getListFromString(ct.getImediaInfo(), " ", 34, 6);
        details.forEach(d -> {
            if (d.length() > 34) {
                result.add(d + "/* hidden string */");
            } else {
                result.add(d);
            }
        });
        return result;
    }

    /**
     * @param in        исходная строка
     * @param delimiter разделитель слов
     * @param maxLetter максимальное количество знаков в строке
     * @param maxRows   максимальное количество строк
     */
    private List<String> getListFromString(String in, String delimiter, int maxLetter, int maxRows) {

        List<String> result = new ArrayList<>();
        if (in.length() <= maxLetter) {
            result.add(in);
            return result;
        }
        String[] words = in.split(delimiter);
        int counter = 1;

        StringBuilder part = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            if ((part.length() + words[i].length() + 1 <= maxLetter) || (counter == maxRows)) {
                part.append(" ").append(words[i]);
            } else {
                counter++;
                result.add(part.toString());
                part = new StringBuilder();
                part.append(words[i]);
            }
        }
        result.add(part.toString());
        return result;
    }
}
