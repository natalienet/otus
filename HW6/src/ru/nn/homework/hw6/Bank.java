package ru.nn.homework.hw6;

import java.util.*;

public class Bank {
    private Map<Client, List<Account>> clientAccounts;

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.clientAccounts = createClientAccounts();

        Client client = new Client("Чижиков Егор Андреевич");
        List<Account> clientAccounts = bank.getAccounts(client);
        if (clientAccounts == null) {
            System.out.println("Счета клиента " + client.toString() + " не найдены");
        } else {
            System.out.println("Счета клиента " + client.toString() + ": " + clientAccounts);
        }

        Account account = new Account(23456789);
        Client foundClient = bank.findClient(account);
        if (foundClient == null) {
            System.out.println("Клиент не найден!");
        } else {
            System.out.println(account.toString() + " принадлежит клиенту " + foundClient.toString());
        }
    }

    private static Map<Client, List<Account>> createClientAccounts() {
        Map<Client, List<Account>> clientAccounts = new HashMap<>();

        Client client1 = new Client("Иванов Петр Сергеевич");
        Account account1_1 = new Account(12345678);
        Account account1_2 = new Account(23456789);
        List<Account> accounts1 = new ArrayList<Account>();
        accounts1.add(account1_1);
        accounts1.add(account1_2);
        clientAccounts.put(client1, accounts1);

        Client client2 = new Client("Петров Иван Семенович");
        Account account2_1 = new Account(87654321);
        Account account2_2 = new Account(987654321);
        Account account2_3 = new Account(34567890);
        List<Account> accounts2 = new ArrayList<Account>();
        accounts2.add(account2_1);
        accounts2.add(account2_2);
        accounts2.add(account2_3);
        clientAccounts.put(client2, accounts2);

        Client client3 = new Client("Чижиков Егор Андреевич");
        Account account3_1 = new Account(13572468);
        List<Account> accounts3 = new ArrayList<Account>();
        accounts3.add(account3_1);
        clientAccounts.put(client3, accounts3);

        return clientAccounts;
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

        Set<Map.Entry<Client, List<Account>>> entrySet = clientAccounts.entrySet();
        for (Map.Entry<Client, List<Account>> pair : entrySet) {
            if (pair.getValue().contains(account)) {
                return pair.getKey();
            }
        }
        return null;
    }
}
