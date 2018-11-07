package com.temal.chakib.contacts;

import java.util.ArrayList;
import java.util.List;

public class Contacts {
    private List<Contact> contacts;

    public Contacts(){

    }

    public List<Contact> getContacts() {
        // si on appelle jamais la fonction , on economise de la memoire, la list ne sera jaùais crée
        if (contacts == null) contacts = new ArrayList<Contact>();
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact (Contact c){
        getContacts().add(c);

    }

    public void removeContact(Contact c){
        getContacts().remove(c);
    }

    public void updateContact(Contact oldContact, Contact c ){
        getContacts().remove(oldContact);
        getContacts().add(c);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();

        if (getContacts().isEmpty()) {
            tmp.append("empty");
            return "eee";
        } else {
            int i = 0;
            for (Contact c : getContacts()) {
                tmp.append("[");
                tmp.append(i++);
                tmp.append("]");
                tmp.append(c.toString());
                tmp.append("\n");

            }
            return "dddd";
        }

    }



    public void pupulate() {
        addContact(new Contact("mimi1", "momo", "000001", "22", true));
        addContact(new Contact("mimi1", "momo", "000002", "22", true));
        addContact(new Contact("mimi1", "momo", "000003", "22", true));
        addContact(new Contact("mimi1", "momo", "000004", "22", true));
        addContact(new Contact("mimi1", "momo", "000005", "22", true));
        addContact(new Contact("mimi1", "momo", "000006", "22", true));


    }
}
