package com.rwsd.book.chapter_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzerSimple {

	private static final String RESOURCES = "src/main/resources/";
	
	public static void main(String[] args) throws IOException {
		
		final Path path = Paths.get(RESOURCES + args[0]);
		final List<String> lines = Files.readAllLines(path);
		
		final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
		
		final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
		
		System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
		System.out.println("Transactions in January " + selectInMonths(bankTransactions, Month.JANUARY));
	}

	public static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
		double total = 0d;
		for(BankTransaction bankTransaction: bankTransactions) {
			total += bankTransaction.getAmount();
		}
		
		return total;
	}
	
	public static List<BankTransaction> selectInMonths(final List<BankTransaction> bankTransactions, final Month month) {
		final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
		for(final BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				bankTransactionsInMonth.add(bankTransaction);
			}
		}
		return bankTransactionsInMonth;
	}
}
