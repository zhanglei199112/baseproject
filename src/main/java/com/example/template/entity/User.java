package com.example.template.entity;

import com.example.template.config.translatorconfig.Code2Text;
import com.example.template.config.translatorconfig.CodeI18n;
import com.example.template.config.translators.AgeTranslator;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tb_user_")
@Getter
@Setter
@CodeI18n
public class User implements Serializable {
    @Id
    @Column(name = "id_")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_")
    private String name;

    @Column(name = "age_")
    @Code2Text(translateor = AgeTranslator.class)
    private Integer age;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}