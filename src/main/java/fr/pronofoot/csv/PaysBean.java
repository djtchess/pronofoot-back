package fr.pronofoot.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class PaysBean {

    @CsvCustomBindByName(column = "id", converter = ConverterLongToString.class, required = true)
    @CsvBindByPosition(position = 0)
    private Long id;

    @CsvBindByName(column = "nomPays")
    @CsvBindByPosition(position = 1)
    private String nomPays;

    @CsvBindByName(column = "drapeau")
    @CsvBindByPosition(position = 2)
    private String drapeau;

    @CsvBindByName(column = "apiId")
    @CsvBindByPosition(position = 3)
    private Long apiId;


   
}