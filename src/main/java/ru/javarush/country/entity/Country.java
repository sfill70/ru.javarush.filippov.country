package ru.javarush.country.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "world", name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @Column(name = "code_2", length = 2, nullable = false)
    private String alternativeCode;

    @Column(name = "name",length = 52, nullable = false)
    private String name;

    @Column(name = "continent", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private Continent continent;

    @Column(name = "region",length = 26, nullable = false)
    private String region;

    @Column(name = "surface_area", nullable = false)
    private BigDecimal surfaceArea;

    @Column(name = "indep_year")
    private Short indepYear;

    @Column(name = "population", nullable = false)
    private Integer population;

    @Column(name = "life_expectancy")
    private BigDecimal lifeExpectancy;

    @Column(name = "gnp")
    private BigDecimal GNP;

    @Column(name = "gnpo_id")
    private BigDecimal GNPOId;

    @Column(name = "local_name", length = 45, nullable = false)
    private String localName;

    @Column(name ="government_form", length = 45, nullable = false)
    private String governmentForm;

    @Column(name ="head_of_state", length = 60)
    private String headOfState;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capital")
//    @NotNull
    private City capital;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Set<CountryLanguage> languages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlternativeCode() {
        return alternativeCode;
    }

    public void setAlternativeCode(String alternativeCode) {
        this.alternativeCode = alternativeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Short getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Short indepYear) {
        this.indepYear = indepYear;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public BigDecimal getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(BigDecimal lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public BigDecimal getGNP() {
        return GNP;
    }

    public void setGNP(BigDecimal GNP) {
        this.GNP = GNP;
    }

    public BigDecimal getGNPOId() {
        return GNPOId;
    }

    public void setGNPOId(BigDecimal GNPOId) {
        this.GNPOId = GNPOId;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public Set<CountryLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<CountryLanguage> languages) {
        this.languages = languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (!Objects.equals(id, country.id)) return false;
        if (!Objects.equals(code, country.code)) return false;
        if (!Objects.equals(alternativeCode, country.alternativeCode))
            return false;
        if (!Objects.equals(name, country.name)) return false;
        if (continent != country.continent) return false;
        if (!Objects.equals(region, country.region)) return false;
        if (!Objects.equals(surfaceArea, country.surfaceArea)) return false;
        if (!Objects.equals(indepYear, country.indepYear)) return false;
        if (!Objects.equals(population, country.population)) return false;
        if (!Objects.equals(lifeExpectancy, country.lifeExpectancy))
            return false;
        if (!Objects.equals(GNP, country.GNP)) return false;
        if (!Objects.equals(GNPOId, country.GNPOId)) return false;
        if (!Objects.equals(localName, country.localName)) return false;
        if (!Objects.equals(governmentForm, country.governmentForm))
            return false;
        return Objects.equals(headOfState, country.headOfState);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (alternativeCode != null ? alternativeCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (surfaceArea != null ? surfaceArea.hashCode() : 0);
        result = 31 * result + (indepYear != null ? indepYear.hashCode() : 0);
        result = 31 * result + (population != null ? population.hashCode() : 0);
        result = 31 * result + (lifeExpectancy != null ? lifeExpectancy.hashCode() : 0);
        result = 31 * result + (GNP != null ? GNP.hashCode() : 0);
        result = 31 * result + (GNPOId != null ? GNPOId.hashCode() : 0);
        result = 31 * result + (localName != null ? localName.hashCode() : 0);
        result = 31 * result + (governmentForm != null ? governmentForm.hashCode() : 0);
        result = 31 * result + (headOfState != null ? headOfState.hashCode() : 0);
        return result;
    }
}
