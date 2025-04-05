package models;

import common.IssueType;
import common.Priority;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Issue {
    IssueType type;
    String summary;
    String description;
    Priority priority;
    String reporter;
    List<String> assignees;
}
