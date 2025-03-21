package com.easyndic.dto;
import java.time.LocalDate;

public class CondominiumAcquisitionDto {
    private Integer userId;
    private Integer condominiumId;
    private LocalDate acquisitionDate;
    private Double unitShare;

    // Constructeurs, Getters et Setters
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getCondominiumId() { return condominiumId; }
    public void setCondominiumId(Integer condominiumId) { this.condominiumId = condominiumId; }

    public LocalDate getAcquisitionDate() { return acquisitionDate; }
    public void setAcquisitionDate(LocalDate acquisitionDate) { this.acquisitionDate = acquisitionDate; }

    public Double getUnitShare() { return unitShare; }
    public void setUnitShare(Double unitShare) { this.unitShare = unitShare; }
}
