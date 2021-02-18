package zda.training.phone;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;

    public static void main(String[] args) {
        contacts = new ArrayList<>();
        System.out.println("Bem vindo ao phone ZDA!");

        showInitialOptions();
    }

    private static void showInitialOptions() {
        int choice;

        System.out.println("Opções:" +
                "\n\t1. Contatos" +
                "\n\t2. Mensagens" +
                "\n\t3. Sair" );

        scanner = new Scanner(System.in);
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default: break;
        }
    }

    private static void manageContacts() {
        int choice;

        System.out.println("Selecione uma ação:" +
                "\n\t1. Todos" +
                "\n\t2. Novo" +
                "\n\t3. Procurar" +
                "\n\t4. Apagar" +
                "\n\t5. Voltar" );

        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showAllContacts();
                break;
            case 2:
                createNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void showAllContacts() {
        for (Contact c: contacts) {
            c.getDetails();
            System.out.println("-=-=-=-=-=-=-=-");
        }

        showInitialOptions();
    }

    private static void createNewContact() {
        System.out.println("Nome: ");
        String name = scanner.next();

        System.out.println("Número: ");
        String number = scanner.next();

        System.out.println("Email: ");
        String email = scanner.next();

        if (name.equals("") || number.equals("") || email.equals("")) {
            System.out.println("Preencha todos os campos!");
            createNewContact();
        } else {
            boolean exists = false;

            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    exists = true;
                }
            }

            if (exists) {
                System.out.println("Contato já inserido!");
                createNewContact();
            } else {
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
            }
        }

        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("Nome: ");
        String name = scanner.next();

        if (name.equals("")) {
            System.out.println("Digite o Nome a ser buscado!");
            searchForContact();
        } else {
            boolean exists = false;
            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    exists = true;
                    c.getDetails();
                }
            }

            if (!exists) {
                System.out.println("Contato não cadastrado!");
            }
        }

        showInitialOptions();
    }

    private static void deleteContact() {
        System.out.println("Nome: ");
        String name = scanner.next();

        if (name.equals("")) {
            System.out.println("Digite o Nome a ser apagado!");
            deleteContact();
        } else {
            boolean exists = false;
            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    exists = true;
                    contacts.remove(c);
                }
            }

            if (!exists) {
                System.out.println("Contato não cadastrado!");
            }
        }

        showInitialOptions();
    }

    private static void manageMessages() {
        int choice;

        System.out.println("Selecione uma ação:" +
                "\n\t1. Todas" +
                "\n\t2. Nova" +
                "\n\t5. Voltar" );

        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showAllMessages();
                break;
            case 2:
                createNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();

        for (Contact c: contacts) {
            allMessages.addAll(c.getMessages());
        }

        if (allMessages.size() > 0) {
            for (Message m: allMessages) {
                m.getDetails();
                System.out.println("-=-=-=-=-=-=-=-");
            }
        }

        showInitialOptions();
    }

    private static void createNewMessage() {
        System.out.println("Destinatário: ");
        String name = scanner.next();

        if (name.equals("")) {
            System.out.println("Digite o nome do destinatário!");
            createNewMessage();
        } else {
            boolean exists = false;

            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    exists = true;
                }
            }

            if (exists) {
                System.out.println("Mensagem:");
                String text = scanner.next();

                if(text.equals("")) {
                    System.out.println("Digite uma mensagem!");
                    createNewMessage();
                } else {
                    id++;
                    Message newMessage = new Message(id, text, name);

                    for (Contact c: contacts) {
                        if (c.getName().equals(name)) {
                            ArrayList<Message> newMessages = c.getMessages();

                            newMessages.add(newMessage);
                            c.setMessages(newMessages);
                        }
                    }
                }
            } else {
                System.out.println("Contato não encontrado!");
                manageMessages();
            }
        }

        showInitialOptions();
    }
}
