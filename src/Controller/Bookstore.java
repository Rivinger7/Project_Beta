/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.*;
import Model.Book;
import Model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Bookstore {

    private List<Book> bookList = null;
    private List<User> userList = null;
    Scanner sc = new Scanner(System.in);
//    private int fID = 1;

    public Bookstore(String path1, String path2) throws Exception {
        bookList = loadBooks(path1);
        userList = loadUsers(path2);
    }

    public List<Book> loadBooks(String path1) throws Exception {
        try {
            File bookInfo = new File(path1);
            String fullPath = bookInfo.getAbsolutePath();
            FileInputStream bookInfoByte = new FileInputStream(fullPath);
            BufferedReader myInputBooksInfo = new BufferedReader(new InputStreamReader(bookInfoByte));
            String thisLine;
            while ((thisLine = myInputBooksInfo.readLine()) != null) {
                Book book = null;
                if (!thisLine.trim().isEmpty()) {
                    String split[] = thisLine.split(",");
//                    String id = split[1].trim();
                    String name = split[2].trim();
                    String type = split[3].trim();
                    double price = Double.parseDouble(split[4].trim());
                    int quantity = Integer.parseInt(split[5].trim());

                    book = new Book(price, name, type, quantity);
                }
                if (bookList == null) {
                    bookList = new ArrayList<>();
                }
                bookList.add(book);
            }
            myInputBooksInfo.close();
        } catch (Exception ex) {
            throw ex;
        }
//        for (Book obj1 : bookList) {
//            System.out.println(obj1);
//            System.out.println("");
//        }
        return bookList;
    }

    public List<User> loadUsers(String path2) throws Exception {
        try {
            File userInfo = new File(path2);
            String fullPath = userInfo.getAbsolutePath();
            FileInputStream userInfoByte = new FileInputStream(fullPath);
            BufferedReader myInputUsersInfo = new BufferedReader(new InputStreamReader(userInfoByte));
            String thisLine;
            HashMap<String, String> userSystem = null;
            while ((thisLine = myInputUsersInfo.readLine()) != null) {
                User user = null;
                if (!thisLine.trim().isEmpty()) {
                    String split[] = thisLine.split(",");
                    if (userSystem == null) {
                        userSystem = new HashMap<>();
                    }
                    String userName = split[1].trim();
                    String password = split[2].trim();

                    user = new User(userName, password);

                    userSystem.put(userName, password);
                }
                if (userList == null) {
                    userList = new ArrayList<>();
                }
                userList.add(user);
            }
            myInputUsersInfo.close();
        } catch (Exception ex) {
            throw ex;
        }
//        for (User obj1 : userList) {
//            System.out.println(obj1);
//            System.out.println("");
//        }
        return userList;
    }

    public boolean checkUser() {
        try {
            System.out.println("\nLogin System");
            System.out.print("User Name: ");
            String userName = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            for (User obj1 : userList) {
                if (userName.equals(obj1.getUserName()) && password.equals(obj1.getPassword())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        System.out.println("Username or Password is invalid");
        return false;
    }

    // Other Options
    
    // Adding and Removing function
    
    public List<Book> addBook() {
        // Nên thêm chức năng nhập vào mỗi function
        System.out.print("Enter the book name: ");
        String name = sc.nextLine();
        System.out.print("Enter the type book: ");
        String type = sc.nextLine();
        System.out.print("Enter the price: ");
        double price = sc.nextDouble();
        System.out.print("Enter the quantity: ");
        int quantity = sc.nextInt();
        sc.skip("\n");

        Book book = new Book(price, name, type, quantity);
        bookList.add(book);
        return bookList;
    }

    // Remove book by ID
    
    public List<Book> removeBook() {
        System.out.print("Enter the ID book: ");
        String id = sc.nextLine();
        int i = 0;
        // Nên tạo clone cho object Book hoặc new Book(bookList)
        for (Book obj : bookList) {
            if (obj.getIdBook().equals(id)) {
                bookList.remove(i);
                return bookList;
            }
            ++i;
        }
        return bookList;
    }

    public <E> boolean writeFile(String path, List<E> list) throws Exception {
        int fID = 1;
        try {
            FileWriter writer = new FileWriter(path);
            for (E book : list) {
                writer.write(fID++ + "," + book.toString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("File " + path + " has been written successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("An error occurred while writing to the file: " + path);
            ex.printStackTrace();
            return false;
        }
        // Add thêm sách vào file.txt thì cần kết hợp thêm kỹ thuật viết file (WirteFile)
    }

    // Sorting following Price, Name, Type and Quantity
    
    public List<Book> sortByPrice() {
        List<Book> sortingList = new ArrayList<>(bookList);
        Collections.sort(sortingList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
        return sortingList;
    }

    public List<Book> sortByName() {
        List<Book> sortingList = new ArrayList<>(bookList);
        Collections.sort(sortingList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getBookName().compareTo(o2.getBookName());
            }

        });
        return sortingList;
    }

    public List<Book> sortByType() {
        List<Book> sortingList = new ArrayList<>(bookList);
        Collections.sort(sortingList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getBookType().compareTo(o2.getBookType());
            }

        });
        return sortingList;
    }

    public List<Book> sortByQuantity() {
        List<Book> sortingList = new ArrayList<>(bookList);
        Collections.sort(sortingList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Integer.compareUnsigned(o1.getQuantity(), o2.getQuantity());
            }

        });
        return sortingList;
    }
}
