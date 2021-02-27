package com.tank.convertor;

import com.google.common.collect.Sets;
import io.vavr.control.Option;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.util.Objects;
import java.util.Set;

/**
 * @param <T>
 * @author tank198435163.com
 */
@SuppressWarnings("unchecked")
public class Node<T extends AbstractItem> extends AbstractItem {

  @SuppressWarnings("unchecked")
  public void addItem(@NonNull final T data) {

    if (this.find(data.getId()).isDefined()) {
      return;
    }
    
    if (Objects.isNull(this.p)) {
      this.setDesc(data.getDesc());
      this.setId(0);
      this.setParentId(0);
      this.p = this;
      return;
    }
    val result = this.findInsertNode(data);
    if (!result.isDefined()) {
      val leafNode = new Leaf();
      leafNode.setParentId(data.getParentId());
      leafNode.setId(data.getId());
      leafNode.setDesc(data.getDesc());
      this.getChildren().add(leafNode);
      return;
    }
    result.filter(r -> r instanceof Node).forEach(r -> {
      val point = ((Node<T>) r);
      point.getChildren().add(data);
      this.p = point;
    });
  }

  public Option<AbstractItem> findInsertNode(@NonNull final T source) {
    return this.findInsertNode(source, this);
  }

  public Option<AbstractItem> find(@NonNull final Integer id) {
    return this.find(id, this);
  }


  private Option<AbstractItem> find(@NonNull final Integer id, @NonNull final Node<T> target) {

    for (AbstractItem child : target.getChildren()) {
      if (child.getId().compareTo(id) == 0) {
        return Option.some(child);
      } else {
        if (child instanceof Node) {
          val resultOpt = this.find(id, ((Node<T>) child));
          if (resultOpt.isDefined()) {
            return resultOpt;
          }
        }
      }
    }

    return Option.none();
  }


  @SuppressWarnings("unchecked")
  private Option<AbstractItem> findInsertNode(@NonNull final T source,
                                              @NonNull final Node<T> target) {

    for (AbstractItem child : target.getChildren()) {
      if (child.getId().compareTo(source.getParentId()) == 0) {
        if (child instanceof Node) {
          this.p = ((Node<T>) child);
          return Option.of(child);
        }
        target.getChildren().remove(child);
        val tmpNode = new Node<T>();
        tmpNode.setId(child.getId());
        tmpNode.setDesc(child.getDesc());
        tmpNode.setParentId(child.getParentId());
        target.getChildren().add(tmpNode);
        this.p = tmpNode;
        return Option.of(tmpNode);
      } else {
        if (child instanceof Node) {
          val result = this.findInsertNode(source, ((Node<T>) child));
          if (Objects.nonNull(result) && result.isDefined()) {
            return result;
          }
        }
      }
    }

    return Option.none();
  }


  @Getter
  private final Set<AbstractItem> children = Sets.newConcurrentHashSet();

  private Node<T> p;
}
