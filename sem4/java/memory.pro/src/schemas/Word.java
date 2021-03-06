package schemas;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Word {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "word", nullable = false)
    private String word;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return id == word1.id &&
                Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word);
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }
}
