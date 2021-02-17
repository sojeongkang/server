package wsjmdev.wsjmdev.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import wsjmdev.wsjmdev.domain.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

    public Account findByUsername(String username);
    public Account findByUsernameAndPassword(String username, String password);
}
