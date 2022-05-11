package edu.aynu.project.entity;

import lombok.Data;

@Data
public class ProjectStudent {

    private Integer id;
    private Integer projectId;
    private Integer studentId;

    public ProjectStudent() {
    }

    public ProjectStudent(Integer projectId, Integer studentId) {
        this.projectId = projectId;
        this.studentId = studentId;
    }
}
