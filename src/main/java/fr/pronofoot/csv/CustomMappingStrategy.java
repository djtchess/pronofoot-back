package fr.pronofoot.csv;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        final int numColumns = getFieldMap().values().size();
        super.generateHeader(bean);

        String[] header = new String[numColumns];

        BeanField beanField;
        for (int i = 0; i < numColumns; i++) {
            beanField = findField(i);
            String columnHeaderName = extractHeaderName(beanField);
            header[i] = columnHeaderName;
        }
        return header;
    }

    private String extractHeaderName(final BeanField beanField) {
        if (beanField == null || beanField.getField() == null) {
            return StringUtils.EMPTY;
        }

        Field field = beanField.getField();

        if (field.getDeclaredAnnotationsByType(CsvBindByName.class).length != 0) {
            final CsvBindByName bindByNameAnnotation = field.getDeclaredAnnotationsByType(CsvBindByName.class)[0];
            return bindByNameAnnotation.column();
        }

        if (field.getDeclaredAnnotationsByType(CsvCustomBindByName.class).length != 0) {
            final CsvCustomBindByName bindByNameAnnotation = field.getDeclaredAnnotationsByType(CsvCustomBindByName.class)[0];
            return bindByNameAnnotation.column();
        }

        return StringUtils.EMPTY;
    }
}
