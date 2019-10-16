package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.Mailbox;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 public interface MailboxRepository {
    Mailbox save(Mailbox mailbox);
    Mailbox update(Integer id, Mailbox mailbox);
    List<Mailbox> findAll();
    Mailbox findById(@NotNull Integer id);
    List<Mailbox> getRunningtext();
    List<Mailbox> getCarousel();
    List<Mailbox> getNotification();
    List<Mailbox> getNewsinfo();
    void deleteById(@NotNull Integer id);
 }