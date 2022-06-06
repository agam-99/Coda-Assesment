package pojos;

public enum FieldType {
    STRING("String"),
    NUMBER("Number"),
    PHONE_NUMBER("Phone Number"),
    EMAIL("emailId");

    private String text;

    FieldType(String value){
        this.text = value;
    }

    public String getText() {
        return this.text;
    }
}
