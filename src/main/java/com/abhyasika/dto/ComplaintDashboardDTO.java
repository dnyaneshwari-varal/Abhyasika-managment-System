package com.abhyasika.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComplaintDashboardDTO {

    private long totalComplaints;

    private long openComplaints;

    private long inProgressComplaints;

    private long resolvedComplaints;

}