package tech.kibetimmanuel.expensemanagerapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kibetimmanuel.expensemanagerapi.model.Expense;

import java.sql.Date;
import java.util.List;


@Service
public interface ExpenseService {
    Expense getExpenseById(Long id);

    Page<Expense> getAllExpenses(Pageable page);

    void deleteExpenseById(Long id);

    Expense saveExpenseDetails(Expense expense);
    Expense updateExpenseDetails(Long id, Expense expense);

    List<Expense> readByDate(Date startDate, Date endDate, Pageable page);

    List<Expense> readByCategory(String category, Pageable page);
    List<Expense> readByName(String keyword, Pageable page);

}
