package zda.training.phone;

public class Message {
    private int    id;
    private String text;
    private String recipient;

    public Message(int id, String text, String recipient) {
        this.id = id;
        this.text = text;
        this.recipient = recipient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void getDetails() {
        System.out.println("ID: " + this.id + "\nContact Name: " + this.recipient + "\nMessage: " + this.text);
    }
}
