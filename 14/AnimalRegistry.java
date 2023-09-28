import java.util.*;

class Animal {
    private String name;
    private String type;
    private List<String> commands;

    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
        this.commands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", commands=" + commands +
                '}';
    }
}

public class AnimalRegistry {
    private static List<Animal> animals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Определить животное в правильный класс");
            System.out.println("3. Увидеть список команд животного");
            System.out.println("4. Обучить животное новым командам");
            System.out.println("5. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер после считывания числа

            switch (choice) {
                case 1:
                    createAnimal();
                    break;
                case 2:
                    classifyAnimal();
                    break;
                case 3:
                    listCommands();
                    break;
                case 4:
                    teachAnimal();
                    break;
                case 5:
                    System.out.println("Выход из программы.");
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        }
    }

    private static void createAnimal() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите тип животного: ");
        String type = scanner.nextLine();

        Animal animal = new Animal(name, type);
        animals.add(animal);
        System.out.println("Животное " + name + " добавлено в реестр.");
    }

    private static void classifyAnimal() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                System.out.println("Животное " + name + " принадлежит к классу " + animal.getType());
                return;
            }
        }
        System.out.println("Животное с именем " + name + " не найдено.");
    }

    private static void listCommands() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                List<String> commands = animal.getCommands();
                System.out.println("Список команд для животного " + name + ":");
                for (String command : commands) {
                    System.out.println(command);
                }
                return;
            }
        }
        System.out.println("Животное с именем " + name + " не найдено.");
    }

    private static void teachAnimal() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                System.out.print("Введите новую команду для животного " + name + ": ");
                String newCommand = scanner.nextLine();
                animal.addCommand(newCommand);
                System.out.println("Команда добавлена.");
                return;
            }
        }
        System.out.println("Животное с именем " + name + " не найдено.");
    }
}
