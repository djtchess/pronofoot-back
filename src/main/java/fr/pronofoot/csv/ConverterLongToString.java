package fr.pronofoot.csv;

import java.util.ResourceBundle;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class ConverterLongToString extends AbstractBeanField {


    public ConverterLongToString() {
        System.out.println("on passe ici");
    }

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException {
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            LongConverter bc = new LongConverter();
            try {
                return bc.convert(Long.class, value.trim());
            } catch (ConversionException var7) {
                CsvDataTypeMismatchException csve = new CsvDataTypeMismatchException(value, this.field.getType(), ResourceBundle.getBundle("convertGermanToBoolean", this.errorLocale).getString("input.not.boolean"));
                csve.initCause(var7);
                throw csve;
            }
        }
    }

    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException {
        String result = "";

        try {
            if (value != null) {
                result = "toto";
            }

            return result;
        } catch (ClassCastException var5) {
            CsvDataTypeMismatchException csve = new CsvDataTypeMismatchException(ResourceBundle.getBundle("booleanConverterToOuiOrNon", this.errorLocale).getString("field.not.boolean"));
            csve.initCause(var5);
            throw csve;
        }
    }


}
