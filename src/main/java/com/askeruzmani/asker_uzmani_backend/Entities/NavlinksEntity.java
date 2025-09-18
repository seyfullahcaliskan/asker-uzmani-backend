package com.askeruzmani.asker_uzmani_backend.Entities;

import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "navlinks")
public class NavlinksEntity extends BaseEntity {

    @Column(name = "href", nullable = false)
    private String href;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "slug")
    private String slug;

    @Column(name = "category")
    private String category;

    @Column(name = "filter")
    private String filterBy;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "is_home_page")
    private YesNoEnum isHomePage = YesNoEnum.NO;

    @Column(name = "sequence_no", nullable = false)
    private Integer sequenceNo;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public YesNoEnum getIsHomePage() {
        return isHomePage;
    }

    public void setIsHomePage(YesNoEnum isHomePage) {
        this.isHomePage = isHomePage;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}
