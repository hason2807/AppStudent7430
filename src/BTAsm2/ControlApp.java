package BTAsm2;

import java.util.Scanner;

public class ControlApp {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager(100); // Giới hạn 100 sinh viên
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nChọn tùy chọn:");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. In thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Sửa thông tin sinh viên");
            System.out.println("5. Sắp xếp sinh viên theo điểm");
            System.out.println("6. Tìm kiếm sinh viên theo ID");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Dọn buffer

            switch (option) {
                case 1:
                    studentManager.addStudent();
                    break;
                case 2:
                    studentManager.printAllStudents();
                    break;
                case 3:
                    studentManager.removeStudent();
                    break;
                case 4:
                    studentManager.updateStudent();
                    break;
                case 5:
                    studentManager.sortStudentsByMarks();
                    break;
                case 6:
                    studentManager.searchStudentById();
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ.");
            }

            if (keepRunning) {
                System.out.print("Bạn có muốn thực hiện chức năng khác không? (y/n): ");
                String continueOption = scanner.nextLine();
                if (continueOption.equalsIgnoreCase("n")) {
                    keepRunning = false;
                    System.out.println("Thoát chương trình.");
                }
            }
        }
        scanner.close(); // Đóng scanner khi không còn sử dụng
    }
}
