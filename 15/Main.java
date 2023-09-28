import java.util.*;

class Counter implements AutoCloseable {
    private int count = 0;

    public void add() {
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() throws Exception {
        if (count > 0) {
            throw new Exception("Счетчик не был обнулен!");
        }
    }
}

class Animal {
    private String name;
    private String type;
    private String command;
    private boolean trained;

    public Animal(String name, String type, String command) {
        this.name = name;
        this.type = type;
        this.command = command;
        this.trained = false;
    }

    public void train(String newCommand) {
        this.command = newCommand;
        this.trained = true;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCommand() {
        return command;
    }

    public boolean isTrained() {
        return trained;
    }
}

class AnimalRegistry {
    private Counter counter;
    
    List<Animal> animalList = new ArrayList<>();
    public AnimalRegistry() {
        counter = new Counter();
    }

    public void addAnimal(String name, String type, String command) {
        try (Counter c = counter) {
            if (name != null && !name.isEmpty() && type != null && !type.isEmpty() && command != null && !command.isEmpty()) {
                Animal animal = new Animal(name, type, command);
                counter.add();
                animalList.add(animal);
                System.out.println("Животное успешно добавлено в реестр!");
            } else {
                System.out.println("Все поля должны быть заполнены!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void classifyAnimal(Animal animal) {
        if (animal.isTrained()) {
            System.out.println("Животное " + animal.getName() + " классифицировано как " + animal.getType());
        } else {
            System.out.println("Для классификации животное должно быть обучено!");
        }
    }

    public void listCommands(Animal animal) {
        System.out.println("Список команд для животного " + animal.getName() + ": " + animal.getCommand());
    }

    public void trainAnimal(Animal animal, String newCommand) {
        animal.train(newCommand);
        System.out.println("Животное " + animal.getName() + " обучено новой команде: " + newCommand);
    }

    public List<Animal> getAnimaList(){
        return animalList;
    }
}

public class Main {
    public static void main(String[] args) {
        AnimalRegistry registry = new AnimalRegistry();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Классифицировать животное");
            System.out.println("3. Список команд для животного");
            System.out.println("4. Обучить животное новой команде");
            System.out.println("5. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Введите имя животного: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите тип животного: ");
                    String type = scanner.nextLine();
                    System.out.print("Введите команду для животного: ");
                    String command = scanner.nextLine();
                    registry.addAnimal(name, type, command);
                    break;
                case 2:
                    System.out.print("Введите имя животного: ");
                    String animalName = scanner.nextLine();
                    // Предполагается, что у вас есть метод для получения животного по имени
                    Animal animal = getAnimalByName(animalName, registry.getAnimaList()); 
                    if (animal != null) {
                        registry.classifyAnimal(animal);
                    } else {
                        System.out.println("Животное не найдено!");
                    }
                    break;
                case 3:
                    System.out.print("Введите имя животного: ");
                    String animalNameForCommands = scanner.nextLine();
                    // Предполагается, что у вас есть метод для получения животного по имени
                    Animal animalForCommands = getAnimalByName(animalNameForCommands, registry.getAnimaList());
                    if (animalForCommands != null) {
                        registry.listCommands(animalForCommands);
                    } else {
                        System.out.println("Животное не найдено!");
                    }
                    break;
                case 4:
                    System.out.print("Введите имя животного: ");
                    String animalNameForTraining = scanner.nextLine();
                    // Предполагается, что у вас есть метод для получения животного по имени
                    Animal animalForTraining = getAnimalByName(animalNameForTraining, registry.getAnimaList());
                    if (animalForTraining != null) {
                        System.out.print("Введите новую команду для обучения: ");
                        String newCommand = scanner.nextLine();
                        registry.trainAnimal(animalForTraining, newCommand);
                    } else {
                        System.out.println("Животное не найдено!");
                    }
                    break;
                case 5:
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте еще раз.");
                    break;
            }
        }
    }

    // Предполагается, что у вас есть метод для получения животного по имени
    private static Animal getAnimalByName(String name, List<Animal> animalList) {
        for (Animal animal : animalList) {
        if (animal.getName().equals(name)) {
            return animal;
        }
    }
    return null; // Если животное с таким именем не найдено
    }
}
