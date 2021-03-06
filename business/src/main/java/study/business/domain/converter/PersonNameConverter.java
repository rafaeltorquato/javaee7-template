package study.business.domain.converter;

import study.business.domain.model.person.PersonName;

import javax.persistence.AttributeConverter;

public class PersonNameConverter implements AttributeConverter<PersonName, String> {
    @Override
    public String convertToDatabaseColumn(PersonName attribute) {
        String dbColumn = null;
        if(attribute != null) {
            dbColumn = attribute.getName().trim() + " " + attribute.getSurname().trim();
        }
        return dbColumn;
    }

    @Override
    public PersonName convertToEntityAttribute(String dbData) {
        PersonName personName = null;
        if(dbData != null) {
            int i = dbData.indexOf(' ');
            if(i > 0) {
                String name = dbData.substring(0, i);
                String surname = dbData.substring(i);
                personName =  new PersonName(name.trim(), surname.trim());
            }
        }
        return personName;
    }
}
