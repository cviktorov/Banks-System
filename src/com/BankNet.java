package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class BankNet {

	private double[][] credits;
	private double safeLevel;
	private Function<Integer, String> mapToName;

	public BankNet(int count) {
		this.credits = new double[count][count];
		this.safeLevel = 300;
		this.mapToName = a -> Integer.toString(a + 100);
	}

	public BankNet(int count, double safeLevel,
			Function<Integer, String> mapToName) {
		this.credits = new double[count][count];
		this.safeLevel = safeLevel;
		this.mapToName = mapToName;
	}

	public double getSafeLevel() {
		return safeLevel;
	}

	public void setSafeLevel(double safeLevel) {
		this.safeLevel = safeLevel;
	}

	public Function<Integer, String> getMapToName() {
		return mapToName;
	}

	public void setMapToName(Function<Integer, String> mapToName) {
		this.mapToName = mapToName;
	}

	public boolean hasGivenCredit(int i, int j) {
		return credits[i][j] >= 1;
	}

	public void setCredit(int i, int j, double credit) {
		credits[i][j] = credit;
	}

	public double getCredit(int i, int j) {
		return credits[i][j];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < credits.length; i++) {
			for (int j = 0; j < credits.length; j++) {
				sb.append("Bank from: " + mapToName.apply(i) + ", credit: "
						+ credits[i][j] + ", Bank to: " + mapToName.apply(j)
						+ "\n");
			}
		}
		return String.format("Safe level: %d \n %s", (int) safeLevel,
				sb.toString());
	}

	public boolean isSafeBank(int i) {
		double sum = 0;
		for (int k = 0; k < credits.length; k++) {
			sum += credits[i][k];
			sum -= credits[k][i];
		}
		return sum > safeLevel;
	}

	public void invalidateCredits(int i) {
		for (int j = 0; j < credits.length; j++) {
			credits[i][j] = 0;
		}
	}

	public List<String> findUnsafebanks() {
		List<String> unsafeBanks = new ArrayList<>();
		for (int i = 0; i < credits.length; i++) {
			if (!isSafeBank(i)) {
				unsafeBanks.add(mapToName.apply(i));
			}
		}
		return unsafeBanks;
	}
}
