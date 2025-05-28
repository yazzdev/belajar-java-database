package dyaz.io.database.main;

import dyaz.io.database.entity.Comment;
import dyaz.io.database.repository.CommentRepository;
import dyaz.io.database.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryTest {

  private CommentRepository commentRepository;

  @BeforeEach
  void setUp() {
    commentRepository = new CommentRepositoryImpl();
  }

  @Test
  void testInsert() {
    Comment comment = new Comment("dyaz@mail.com", "Hello Guys 2!!");
    commentRepository.insert(comment);

    Assertions.assertNotNull(comment.getId());
    System.out.println(comment.getId());
  }

  @Test
  void testFindById() {
    Comment comment = commentRepository.findById(4307);

    Assertions.assertNotNull(comment.getId());
    System.out.println(comment.getId());
    System.out.println(comment.getEmail());
    System.out.println(comment.getComment());

    Comment notFound = commentRepository.findById(99999);
    Assertions.assertNull(notFound);
  }

  @Test
  void testFindAll() {
    List<Comment> comments = commentRepository.findAll();
    System.out.println(comments.size());
  }

  @Test
  void testFindByEmail() {
    List<Comment> comments = commentRepository.findAllByEmail("dyaz@mail.com");
    System.out.println("Jumlah Data : " + comments.size());

    for (Comment comment : comments) {
      System.out.println(comment.getEmail());
      System.out.println(comment.getComment());
    }
  }
}
