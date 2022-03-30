package models;

public class Skill {
	private String skillName;
	private int skillValue;
	
	public Skill() {
		skillName="Умение";
		skillValue=30;
	}
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public int getSkillValue() {
		return skillValue;
	}
	public void setSkillValue(int skillValue) {
		this.skillValue = skillValue;
	}
}
