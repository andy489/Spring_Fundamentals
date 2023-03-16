package com.gira.model.view;

import com.gira.model.enumerated.ClassificationEnum;
import com.gira.model.enumerated.ProgressEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
public class TaskView {

    private Long id;

    private String name;

    private String assignedTo;

    private ClassificationEnum classification;

    private Date dueDate;

    private ProgressEnum progress;

}
