package study.converter;

import study.domain.model.PersonName;

import javax.persistence.AttributeConverter;

public class PersonNameConverter implements AttributeConverter<PersonName, String> {
    @Override
    public String convertToDatabaseColumn(PersonName attribute) {
        if(attribute != null) {
            return attribute.getName() + " " + attribute.getSurname();
        }
        return null;
    }

    @Override
    public PersonName convertToEntityAttribute(String dbData) {
        if(dbData != null) {
            int i = dbData.indexOf(' ');
            if(i > 0) {
                String name = dbData.substring(0, i);
                String surname = dbData.substring(i);
                return new PersonName(name, surname);
            }
        }

        return null;
    }
}
