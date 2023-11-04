import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Boss boss = new Boss(700, 30, 0);
        Player[] players = {
                new Player("Player 1", 270, 10, Ability.PHYSICAL),
                new Player("Player 2", 300, 15, Ability.MAGIC),
                new Player("Player 3", 310, 20, Ability.KINETIC),
                new Player("Medic", 100, 0, Ability.HEAL)
        };

        int rounds = 6;

        for (int round = 1; round <= rounds; round++) {
            System.out.println("Round " + round);

            int bossDamage = boss.attack(players);

            // Проверка на поражение игроков
            boolean allPlayersDead = true;
            for (Player player : players) {
                if (player.isAlive()) {
                    allPlayersDead = false;
                    break;
                }
            }

            if (allPlayersDead) {
                System.out.println("Босс выиграл.");
                return;
            }

            System.out.println("Босс атаковал игроков и нанес " + bossDamage + " урона.");

            // Проверка победы босса
            if (boss.getHealth() <= 0) {
                System.out.println("Игроки выиграли.");
                return;
            }
        }
    }
}

class Boss {
    private int health;
    private int damage;
    private int defense;
    private Random random;

    public Boss(int health, int damage, int defense) {
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.random = new Random();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int attack(Player[] players) {
        int bossDamage = random.nextInt(9) + 1;  // Выбор случайного числа от 1 до 9
        bossDamage *= damage;

        for (Player player : players) {
            if (player.isAlive()) {
                player.takeDamage(bossDamage);
            }
        }

        return bossDamage;
    }
}

class Player {
    private String name;
    private int health;
    private int damage;
    private Ability ability;

    public Player(String name, int health, int damage, Ability ability) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
}

enum Ability {
    PHYSICAL(10),
    MAGIC(15),
    KINETIC(20),
    HEAL(0);

    private int defense;

    Ability(int defense) {
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }
}
