package fr.pronofoot.csv;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ExportCSV {

    public static <T> String createCsv(List<T> data, Class<T> beanClazz) {
        CustomMappingStrategy<T> mappingStrategy = new CustomMappingStrategy<T>();
        mappingStrategy.setType(beanClazz);

        StringWriter writer = new StringWriter();
        String csv = "";
        try {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withMappingStrategy(mappingStrategy)
                    .withApplyQuotesToAll(false)
                    .build();
            sbc.write(data);
            csv = writer.toString();
        } catch (CsvRequiredFieldEmptyException e) {
            // TODO add some logging...
        } catch (CsvDataTypeMismatchException e) {
            // TODO add some logging...
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
            }
        }
        return csv;
    }


    public static void main(String[] args){

        CustomMappingStrategy<PaysBean> mappingStrategy = new CustomMappingStrategy<PaysBean>();
        mappingStrategy.setType(PaysBean.class);

        HeaderColumnNameMappingStrategy<PaysBean> beanStrategy = new HeaderColumnNameMappingStrategy<PaysBean>();
        beanStrategy.setType(PaysBean.class);

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("Z:/pays.csv"));
            CSVReader csvReader = new CSVReader(reader);



               CsvToBean<PaysBean> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(PaysBean.class)
                        .withMappingStrategy(beanStrategy)
                        .withSeparator(';')
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

            Iterator<PaysBean> iterator = csvToBean.iterator();

            while (iterator.hasNext()) {
                PaysBean paysBean = iterator.next();
                System.out.println(paysBean.getNomPays());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<PaysBean> paysBeanList = new ArrayList<>();
        paysBeanList.add(new PaysBean(1L,"france","",10L));

        StringWriter writer = new StringWriter();
        String csv = "";
        try {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .withMappingStrategy(beanStrategy)
                    .withApplyQuotesToAll(false)
                    .build();
            sbc.write(paysBeanList);
            csv = writer.toString();
        } catch (CsvRequiredFieldEmptyException e) {
            // TODO add some logging...
        } catch (CsvDataTypeMismatchException e) {
            // TODO add some logging...
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
            }
        }
        System.out.println(csv);


    }


}
