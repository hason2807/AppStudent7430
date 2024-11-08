package BTAsm2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManager {
    private Student[] students;
    private int studentCount;
    private Scanner scanner = new Scanner(System.in);

    public StudentManager(int maxStudents) {
        students = new Student[maxStudents];
        studentCount = 0;
    }

    public void addStudent() {
        while (true) {
            try {
                System.out.println("Nhập vào thông tin sinh viên:");
                System.out.print("Nhập vào ID sinh viên (hoặc 0 để dừng): ");
                int id = scanner.nextInt();
                if (id == 0) {
                    break;
                }

                // Kiểm tra ID phải bằng ID cuối cùng + 1
                if (studentCount > 0) {
                    int expectedId = students[studentCount - 1].getId() + 1;
                    if (id != expectedId) {
                        System.out.println("ID phải là " + expectedId + ". Vui lòng nhập lại.");
                        continue;
                    }
                }
                scanner.nextLine(); // Dọn buffer
                System.out.print("Nhập vào tên sinh viên: ");
                String name = scanner.nextLine();

                // Kiểm tra tên không chứa số
                if (!name.matches("[a-zA-Z\\s]+")) {
                    System.out.println("Tên sinh viên không được chứa số. Vui lòng nhập lại.");
                    continue;
                }

                System.out.print("Nhập vào điểm sinh viên: ");
                double score = scanner.nextDouble();
                scanner.nextLine(); // Dọn buffer

                if (score < 0 || score > 10) {
                    System.out.println("Điểm không hợp lệ, vui lòng nhập từ 0 đến 10.");
                    continue;
                }

                if (studentCount < students.length) {
                    students[studentCount++] = new Student(id, name, score);
                    System.out.println("Đã thêm sinh viên thành công.");
                } else {
                    System.out.println("Không thể thêm sinh viên, đã đạt giới hạn.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
                scanner.nextLine(); // Dọn buffer để tránh lặp vô tận
            }
        }
    }
    public void printAllStudents() {
        if (studentCount == 0) {
            System.out.println("Không có sinh viên nào.");
            return;
        }
        for (int i = 0; i < studentCount; i++) {
            students[i].printInfo();
        }
    }

    public void removeStudent() {
        try {
            System.out.println("Danh sách trước khi xóa:");
            for (int i = 0; i < studentCount; i++) {
                students[i].printInfo();
            }

            System.out.print("Nhập ID sinh viên cần xóa: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            boolean found = false;
            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    // Dịch chuyển các phần tử
                    for (int j = i; j < studentCount - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    students[--studentCount] = null; // Giải phóng bộ nhớ
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Đã xóa sinh viên với ID: " + id);
            } else {
                System.out.println("Không tìm thấy sinh viên với ID: " + id);
            }

            System.out.println("\nDanh sách sau khi xóa:");
            for (int i = 0; i < studentCount; i++) {
                students[i].printInfo();
            }
        } catch (InputMismatchException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
            scanner.nextLine(); // Dọn buffer
        }
    }

    public void updateStudent() {
        try {
            System.out.print("Nhập ID sinh viên cần sửa: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    System.out.print("Tên mới: ");
                    String newName = scanner.nextLine();
                    System.out.print("Điểm mới: ");
                    double newMarks = scanner.nextDouble();
                    scanner.nextLine();

                    if (newMarks < 0 || newMarks > 10) {
                        System.out.println("Điểm không hợp lệ, vui lòng nhập từ 0 đến 10.");
                        return;
                    }
                    students[i].setName(newName);
                    students[i].setMarks(newMarks);
                    System.out.println("Đã cập nhật thông tin sinh viên.");
                    return;
                }
            }
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        } catch (InputMismatchException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
            scanner.nextLine(); // Dọn buffer
        }

    }

    public void sortStudentsByMarks() {
        System.out.println("Danh sách trước khi sắp xếp:");
        for (int i = 0; i < studentCount; i++) {
            students[i].printInfo();
        }
        // Sắp xếp sinh viên theo điểm
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j].getMarks() > students[j + 1].getMarks()) {
                    // Hoán đổi vị trí sinh viên
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        // Cập nhật lại ID theo thứ tự mới
        for (int i = 0; i < studentCount; i++) {
            students[i].setId(i + 1);
        }
        System.out.println("\nDanh sách sau khi sắp xếp theo điểm và cập nhật ID:");
        for (int i = 0; i < studentCount; i++) {
            students[i].printInfo();
        }

        System.out.println("Đã sắp xếp sinh viên theo điểm (tăng dần) và cập nhật lại ID.");
    }

    public void searchStudentById() {
        try {
            System.out.print("Nhập ID sinh viên cần tìm: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    students[i].printInfo();
                    return;
                }
            }
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        } catch (InputMismatchException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
            scanner.nextLine(); // Dọn buffer
        }
    }

    // Đóng scanner khi không còn sử dụng
    public void closeScanner() {
        scanner.close();
    }
}
