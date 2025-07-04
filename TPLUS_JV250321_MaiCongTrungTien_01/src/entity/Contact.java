package entity;

import presentation.ContactApplication;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Contact implements IContact {
    private int id;
    private String name;
    private String email;
    private String phone;
    private boolean sex;
    private String address;
    private byte rating;
    private String note;

    public Contact() {
    }

    public Contact(String address, String email, int id, String name, String note, String phone, byte rating, boolean sex) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.name = name;
        this.note = note;
        this.phone = phone;
        this.rating = rating;
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public void inputData(Scanner sc) {
        this.id = inputId(sc);
        this.name = inputName(sc);
        this.email = inputEmail(sc);
        this.phone = inputPhone(sc);
        this.sex = inputSex(sc);
        this.address = inputAddress(sc);
        this.rating = inputRating(sc);
        this.note = inputNote(sc);

    }


    @Override
    public void displayData(Scanner sc) {
        System.out.println("ID :" + id);
        System.out.println("Name :" + name);
        System.out.println("Email : " + email);
        System.out.println("phone : " + phone);
        System.out.println("sex : " + (true ? "nam" : "nữ"));
        System.out.println("Address :" + address);
        displayRating(rating);
        System.out.println("Note : " + note);
        System.out.println("-----------");
    }

    public int inputId(Scanner sc) {
        if (ContactApplication.contacts[0] == null) {
            return 1;
        } else {
            int idMax = ContactApplication.contacts[0].getId();
            for (int i = 0; i < ContactApplication.currentIndex; i++) {
                if (ContactApplication.contacts[i] != null && ContactApplication.contacts[i].getId() > idMax) {
                    idMax = ContactApplication.contacts[i].getId();
                }
            }
            return idMax + 1;
        }
    }

    public String inputName(Scanner sc) {
        System.out.println("họ và tên liên hệ :");
        do {
            String inputName = sc.nextLine();
            if (isTemp(inputName)) {
                System.err.println("tên liên hệ không được để trống vui lòng nhập lại!!");
            } else {
                if (inputName.length() > 50) {
                    System.err.println("tên liên hệ tối đa 50 kí tự!!");
                } else {
                    return inputName;
                }
            }

        } while (true);
    }

    public String inputEmail(Scanner sc) {
        System.out.println("mời bạn nhập email :");
        String emaiRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{3,}$";
        do {
            String inputEmail = sc.nextLine();
            if (isTemp(inputEmail)) {
                System.err.println("vui long không để trống email!");
            } else {
                if (Pattern.matches(emaiRegex, inputEmail)) {
                    return inputEmail;
                } else {
                    System.err.println("mời nhâp đúng định dang email!!");
                }
            }
        } while (true);
    }

    public String inputPhone(Scanner sc) {
        System.out.println("Nhập số điện thoại :");
        String phoneRegex = "^0\\d{9,10}$";
        do {
            String inputPhone = sc.nextLine();
            if (isTemp(inputPhone)) {
                System.err.println("số điện thoại không được để trống!!");
            } else {
                if (Pattern.matches(phoneRegex, inputPhone)) {
                    boolean exists = false;
                    for (int i = 0; i < ContactApplication.currentIndex; i++) {
                        if (ContactApplication.contacts[i] != null && ContactApplication.contacts[i].getPhone().trim().equals(inputPhone.trim())) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        System.err.println("số điện thoại trùng lặp vui lòng nhập lại!!");
                    } else {
                        return inputPhone;
                    }
                }
                else {
                    System.err.println("moi nhap dung dinh dang dau la 0 va co 10 den 11 so!");
                }

            }

        } while (true);
    }

    public boolean inputSex(Scanner sc) {
        System.out.println("Giới tính(true - Nam / false - Nữ)\n" +
                "mời bạn lựa chọn :");
        do {
            String inputSex = sc.nextLine();
            if (isTemp(inputSex)) {
                System.out.println("giới tính không được để trống mời bạn chọn true | false");
            } else {
                Boolean sex = Boolean.parseBoolean(inputSex.toLowerCase());
                return sex;
            }

        } while (true);
    }

    public String inputAddress(Scanner sc) {
        System.out.println("mời nhập địa chỉ :");
        do {
            String inputAddress = sc.nextLine();
            if (inputAddress.length() > 100) {
                System.err.println("vui lòng nhập địa chỉ tối đa 100 kí tự");
            } else {
                return inputAddress;
            }

        } while (true);

    }

    public byte inputRating(Scanner sc) {
        System.out.println("1. Không quan trọng");
        System.out.println("2. Ít tương tác");
        System.out.println("3. Bình thường");
        System.out.println("4. Thân thiết");
        System.out.println("5. VIP");
        System.out.print("mời nhập lựa chọn của bạn :");
        String choiceRegex = "[1-5]{1}";
        do {
            String inputRating = sc.nextLine();
            if (isTemp(inputRating)) {
                System.err.println("mời nhập đánh giá của bạn không được để trống!");
            } else {
                if (Pattern.matches(choiceRegex, inputRating)) {
                    return Byte.parseByte(inputRating);
                } else {
                    System.err.println("mời nhập lựa chọn là số từ 1 đến 5!");
                }
            }

        } while (true);
    }

    public String inputNote(Scanner sc) {
        System.out.println("Ghi chú :");
        do {
            String inputNote = sc.nextLine();
            if (inputNote.length() > 100) {
                System.err.println("Ghi chú chỉ tối đa 100 kí tự!");
            } else {
                return inputNote;
            }

        } while (true);

    }

    public static boolean isTemp(String data) {
        return data == null || data.trim().isEmpty();
    }

    public void displayRating(byte number) {
        switch (number) {
            case 1:
                System.out.println("Rating :không quan trọng");
                break;
            case 2:
                System.out.println("Rating :ít tương tác");
                break;
            case 3:
                System.out.println("Rating :Bình thường");
                break;
            case 4:
                System.out.println("Rating : Thân thiết");
                break;
            case 5:
                System.out.println("Rating : VIP");
                break;
        }

    }

}
