package tech.kibetimmanuel.expensemanagerapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.kibetimmanuel.expensemanagerapi.model.Expense;
import tech.kibetimmanuel.expensemanagerapi.service.ExpenseService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
@Slf4j
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public Page<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page);
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseService.deleteExpenseById(id);
    }

    @GetMapping("/{id}")
    public Expense getExpenseById( @PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Expense createExpense(@Valid @RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateExpenseDetails(id, expense);
    }

    @GetMapping("/category")
    public List<Expense> getExpenseByCategory(@RequestParam String category, Pageable page){
        return  expenseService.readByCategory(category, page);
    }

    @GetMapping("/name")
    public List<Expense> getExpenseByName(@RequestParam String name, Pageable page){
        return  expenseService.readByName(name, page);
    }

    @GetMapping("/date")
    public List<Expense> getExpenseByCategory(@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate, Pageable page){
        return  expenseService.readByDate(startDate, endDate, page);
    }
}
