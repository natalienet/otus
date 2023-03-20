package ru.nn.homework.hw6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<Client, List<Account>> clientAccounts;
    private Map<Account, Client> accountClients;

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.createClientsAndAccounts();

        Client client = new Client("Чижиков Егор Андреевич");
        List<Account> clientAccounts = bank.getAccounts(client);
        if (clientAccounts == null) {
            System.out.println("Счета клиента " + client.toString() + " не найдены");
        } else {
            System.out.println("Счета клиента " + client.toString() + ": " + clientAccounts);
        }

        Account account = new Account(987654321);
        Client foundClient = bank.findClient(account);
        if (foundClient == null) {
            System.out.println("Клиент не найден!");
        } else {
            System.out.println(account.toString() + " принадлежит клиенту " + foundClient.toString());
        }
    }

    private void createClientsAndAccounts() {
        clientAccounts = new HashMap<>();
        accountClients = new HashMap<>();

        Client client1 = new Client("Иванов Петр Сергеевич");
        Account account1_1 = new Account(12345678);
        Account account1_2 = new Account(23456789);
        List<Account> accounts1 = new ArrayList<>();
        accounts1.add(account1_1);
        accounts1.add(account1_2);
        clientAccounts.put(client1, accounts1);
        accountClients.put(account1_1, client1);
        accountClients.put(account1_2, client1);

        Client client2 = new Client("Петров Иван Семенович");
        Account account2_1 = new Account(87654321);
        Account account2_2 = new Account(987654321);
        Account account2_3 = new Account(34567890);
        List<Account> accounts2 = new ArrayList<>();
        accounts2.add(account2_1);
        accounts2.add(account2_2);
        accounts2.add(account2_3);
        clientAccounts.put(client2, accounts2);
        accountClients.put(account2_1, client2);
        accountClients.put(account2_2, client2);
        accountClients.put(account2_3, client2);

        Client client3 = new Client("Чижиков Егор Андреевич");
        Account account3_1 = new Account(13572468);
        List<Account> accounts3 = new ArrayList<>();
        accounts3.add(account3_1);
        clientAccounts.put(client3, accounts3);
        accountClients.put(account3_1, client3);
    }

    private List<Account> getAccounts(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("client");
        }
        return clientAccounts.get(client);
    }

    private Client findClient(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("account");
        }

        return accountClients.get(account);
    }
}
