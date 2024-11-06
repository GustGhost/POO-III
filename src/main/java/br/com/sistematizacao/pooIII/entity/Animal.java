package br.com.sistematizacao.pooIII.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "animais")
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "O tipo do animal é obrigatório")
    @Column(name = "tipo")
    private String tipo;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "raça")
    private String raca;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAdocao status;

    @Column(name = "descrição")
    private String descricao;

    @Column(name = "imagem")
    private String imagemUrl;  // URL da imagem
}
