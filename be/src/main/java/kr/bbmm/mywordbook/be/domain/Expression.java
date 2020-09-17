package kr.bbmm.mywordbook.be.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "mwb_expression")
@Getter
@Setter
public class Expression implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Word word;

    private String lang;

    @Size(max = 300)
    @Column(name = "value", length = 300)
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Expression)) {
            return false;
        }
        return id != null && id.equals(((Expression) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
                "lang='" + lang + '\'' +
                "value='" + value + '\'' +
                "}";
    }
}
