package org.example.page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NavMenuButton {

    CHANGE_STATUS("Change status"),
    CREATE_NEW("Create new"),
    EDIT("Edit"),
    SAVE("Save"),
    EXIT("Exit"),
    DELETE("Delete");

    private String buttonName;
}
