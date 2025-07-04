package presentation;

import entity.Contact;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ContactApplication {
    public static Contact[] contacts = new Contact[100];
    public static int currentIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("  ----------------------- Contact Book Menu --------------------\n" +
                    "1. Hiển thị danh sách liên hệ\n" +
                    "2. Thêm các liên hệ mới\n" +
                    "3. Chỉnh sửa thông tin liên hệ\n" +
                    "4. Xóa liên hệ\n" +
                    "5. Tìm kiếm liên hệ \n" +
                    "6. Thống kê số lượng liên hệ theo mức độ quan trọng\n" +
                    "7. Thống kê số lượng liên hệ theo giới tính\n" +
                    "8. Sắp xếp liên hệ theo tên(a-z / z-a)\n" +
                    "9. Thoát chương trình\n");
            int choice = isChoice(sc, 9);
            switch (choice) {
                case 1:
                    displayAll(sc);
                    break;
                case 2:
                    creatContact(sc);
                    break;
                case 3:
                    updateContact(sc);
                    break;
                case 4:
                    deleteContact(sc);
                    break;
                case 5:
                    searchContactById(sc);
                    break;
                case 6:
                    countByRating(sc);
                    break;
                case 7:countBySex(sc);
                    break;
                case 8:softByNameASC(sc);
                    break;
                case 9:
                    System.exit(0);

            }
        } while (true);


    }

    public static void displayAll(Scanner sc) {
        for (int i = 0; i < currentIndex; i++) {
            contacts[i].displayData(sc);
        }
    }

    public static int isChoice(Scanner sc, int maxChoice) {
        System.out.println("mời bạn nhập lựa chọn");
        do {
            String inputChoice = sc.nextLine();
            if (inputChoice == null && inputChoice.trim().isEmpty()) {
                System.err.printf("mời nhập lựa chọn là sô nguên tư 1 đến %d", maxChoice);
            } else {
                int choice = Integer.parseInt(inputChoice);
                if (choice > maxChoice) {
                    System.err.printf("vui lòng nhập số từ 1 đến %d ", maxChoice);
                } else {
                    return choice;
                }
            }

        } while (true);

    }

    public static void creatContact(Scanner sc) {
        contacts[currentIndex] = new Contact();
        contacts[currentIndex].inputData(sc);
        currentIndex++;
    }

    public static void updateContact(Scanner sc) {
        int updateIndex = findById(sc);
        if (updateIndex != -1) {
            boolean loop = true;
            System.out.println("bạn muốn update phần nào :");
            System.out.println("1. name");
            System.out.println("2. email");
            System.out.println("3. phone");
            System.out.println("4. sex");
            System.out.println("5. address");
            System.out.println("6. rating");
            System.out.println("7. note");
            System.out.println("8. exit");
            System.out.print("lựa chọn bạn là :");
            do {
                int choice = isChoice(sc, 8);
                switch (choice) {
                    case 1:
                        contacts[updateIndex].setName(contacts[updateIndex].inputName(sc));
                        break;
                    case 2:
                        contacts[updateIndex].setEmail(contacts[updateIndex].inputEmail(sc));
                        break;
                    case 3:
                        contacts[updateIndex].setPhone(contacts[updateIndex].inputPhone(sc));
                        break;
                    case 4:
                        contacts[updateIndex].setSex(!contacts[updateIndex].isSex());
                        System.out.println("cap nhat doi thanh cong!");
                        break;
                    case 5:
                        contacts[updateIndex].setAddress(contacts[updateIndex].inputAddress(sc));
                        break;
                    case 6:
                        contacts[updateIndex].setRating(contacts[updateIndex].inputRating(sc));
                        break;
                    case 7:
                        contacts[updateIndex].setNote(contacts[updateIndex].inputNote(sc));
                        break;
                    case 8:
                        loop = false;
                }
            } while (loop);
        } else {
            System.err.println("không tồn tại!");
        }
    }

    public static int findById(Scanner sc) {
        System.out.println("Nhập Id muốn tìm để thao tác : ");
        do {
            String inputId = sc.nextLine();
            if (Contact.isTemp(inputId)) {
                System.err.println("Id không được để trống!");
            } else {
                int search = Integer.parseInt(inputId);
                if (search <= 0) {
                    System.err.println("Id > 0");
                } else {
                    boolean exists = false;
                    for (int i = 0; i < currentIndex; i++) {
                        if (contacts[i] != null && contacts[i].getId() == search) {
                            exists = true;
                            return i;
                        }
                    }
                    if (!exists) {
                        return -1;
                    }
                }

            }

        } while (true);
    }

    public static void deleteContact(Scanner sc) {
        int deleteIndex = findById(sc);
        for (int i = deleteIndex; i < currentIndex - 1; i++) {
            contacts[i] = contacts[i + 1];
        }
        contacts[currentIndex - 1] = null;
        currentIndex--;
    }

    public static void searchContactById(Scanner sc) {
        int search = findById(sc);
        contacts[search].displayData(sc);
    }

    public static void countByRating(Scanner sc) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (contacts[i].getRating() == 1) {
                count1++;
            } else if (contacts[i].getRating() == 2) {
                count2++;
            } else if (contacts[i].getRating() == 3) {
                count3++;
            } else if (contacts[i].getRating() == 4) {
                count4++;
            } else {
                count5++;
            }
        }
        System.out.printf("Rating : khong quan trong co- %d ", count1);
        System.out.printf("Rating : it tuong tac co- %d ", count2);
        System.out.printf("Rating : binh thuong - %d ", count3);
        System.out.printf("Rating : than thiet - %d ", count4);
        System.out.printf("Rating : vip - %d ", count5);
    }

    public static void countBySex(Scanner sc) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (contacts[i].isSex()) {
                count1++;
            }else count2++;
        }
        System.out.printf("so luong nam la : %d",count1);
        System.out.printf("so luong nu la : %d",count2);
    }
    public static void softByNameASC(Scanner sc){
        for (int i = 0; i < currentIndex-1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (contacts[i].getName().compareTo(contacts[j].getName())>0){
                    Contact temp = new Contact();
                     temp = contacts[i];
                    contacts[i]=contacts[j];
                    contacts[j]=temp;
                }
            }
        }
    }
    public static void softByNameDESC(Scanner sc){
        for (int i = 0; i < currentIndex-1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (contacts[i].getName().compareTo(contacts[j].getName())<0){
                    Contact temp = new Contact();
                    temp = contacts[i];
                    contacts[i]=contacts[j];
                    contacts[j]=temp;
                }
            }
        }
    }
}

