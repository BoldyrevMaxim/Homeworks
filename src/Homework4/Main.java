public class OOP {
    public static class Worker {
        public final String fullName, post, email, numberPhone;
        public final int salary, age;

        public Worker(String fullName, String post, String email, String numberPhone, int salary, int age) {
            this.fullName = fullName;
            this.post = post;
            this.email = email;
            this.numberPhone = numberPhone;
            this.salary = salary;
            this.age = age;
        }

        public void printInformation() {
            var info = String.format("ФИО: %s\r\nДолжность: %s\r\nemail: %s\r\nТелефон: %s\r\n" +
                            "Зарплата: %s\r\nВозраст: %s", this.fullName, this.post, this.email, this.numberPhone,
                    this.salary, this.age);
            info.lines().forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        Worker[] workerList = new Worker[5];
        workerList[0] = new Worker("QWE", "gg", "thron@snesite.afk", "100079937", 1000, 49);
        workerList[1] = new Worker("ZXC", "glhf", "thron1@snesite.afk", "99379867", 993, 42);
        workerList[2] = new Worker("ASD", "ggwp", "thron2@snesite.afk", "98679797", 986, 35);
        workerList[3] = new Worker("VBN", "lmao", "thron3@snesite.afk", "97979727", 979, 28);
        workerList[4] = new Worker("RTY", "stfu", "thron4@snesite.afk", "97279657", 972, 21);

        for (Worker workers : workerList) {
            if (workers.age > 40) {
                System.out.println("--------------------------");
                workers.printInformation();
            }
        }
        System.out.println("--------------------------");
    }
}
