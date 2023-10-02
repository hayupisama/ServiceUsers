package com.mstradingcards.ServicePlayers.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mstradingcards.ServicePlayers.enums.Rarity;

import lombok.Data;

@Data
public class CartesDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Rarity is required")
    private Rarity rarity;

    @NotNull(message = "Attack value is required")
    @Min(value = 0, message = "Attack value must be at least 0")
    private Integer attack;

    @NotNull(message = "Health value is required")
    @Min(value = 1, message = "Health value must be at least 1")
    private Integer health;

    @NotBlank(message = "Description is required")
    private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
