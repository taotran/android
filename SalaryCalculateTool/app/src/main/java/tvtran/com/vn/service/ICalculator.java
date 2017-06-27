package tvtran.com.vn.service;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/23/2017
 */
public interface ICalculator
{
  double calculate();

  Double calcInsurancesSubtraction(Double salary);

  Double calcDependenciesSubtraction(int numberOfDependencies, Double salaryAfterInsurancesSubtraction);

  Double calcThueTNCNByRange(Double salaryAfterDependenciesSubtraction);

  Double calcFinalSalary(Double salaryAfterInsurances, Double appliedThueTNCNSalary);

  double calcEmployerTotalPaid(Double salary);
}
