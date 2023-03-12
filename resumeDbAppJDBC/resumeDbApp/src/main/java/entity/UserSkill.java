package entity;

public class UserSkill {
    private Integer id ;
    private User user;
    private Skill skill;
    private Integer power;

    public UserSkill(Integer id, User user, Skill skill, Integer power) {
        this.id = id;
        this.user = user;
        this.skill = skill;
        this.power = power;
    }

    public UserSkill() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "UserSkill{" +
                "id=" + id +
                ", user=" + user +
                ", skill=" + skill +
                ", power=" + power +
                '}';
    }
}
