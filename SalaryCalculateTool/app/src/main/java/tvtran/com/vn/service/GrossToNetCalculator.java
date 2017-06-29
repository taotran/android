package tvtran.com.vn.service;

import tvtran.com.vn.entity.Detail;

import java.util.List;
import java.util.Map;

import static tvtran.com.vn.utils.Utils.formattedDouble;
import static tvtran.com.vn.utils.Utils.writeContentToDetailList;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/26/2017
 */
public class GrossToNetCalculator extends AbstractCalculator
{

  //@formatter:off
  private static final double GIAM_TRU_GIA_CANH_BAN_THAN  = 9000000f;
  private static final double GIA_CANH_PHU_THUOC          = 3600000f;

  //@formatter:on

  public GrossToNetCalculator(Double inputSalary, Integer numberOfDependencies, Map<Integer, List<Detail>> detailsMap)
  {
    super(inputSalary, numberOfDependencies, detailsMap);
  }

  public double calculate()
  {
    writeContentToDetailList(detailList, 0, formattedDouble(inputSalary));
    double salaryAfterInsurancesSubtraction = calcInsurancesSubtraction(inputSalary);

    //Thu nhap truoc thue
    writeContentToDetailList(detailList, 4, formattedDouble(salaryAfterInsurancesSubtraction));

    double salaryAfterDependenciesSubtraction = calcDependenciesSubtraction(numberOfDependencies, salaryAfterInsurancesSubtraction);
    double thueTNCNByRange = calcThueTNCNByRange(salaryAfterDependenciesSubtraction);
    double finalSalary = calcFinalSalary(salaryAfterInsurancesSubtraction, thueTNCNByRange);
    System.out.println(finalSalary);
    writeContentToDetailList(detailList, 9, formattedDouble(finalSalary));

    //call to output EmployerDetails only
    calcEmployerTotalPaid(inputSalary);

    return finalSalary;
  }

  @Override
  public Double calcDependenciesSubtraction(int numberOfDependencies, Double salaryAfterInsurancesSubtraction)
  {
    final double dependenciesDeductedAmount = (numberOfDependencies * GIA_CANH_PHU_THUOC);
    final double result = salaryAfterInsurancesSubtraction - GIAM_TRU_GIA_CANH_BAN_THAN - dependenciesDeductedAmount;

    //giam tru gia canh ban than
    writeContentToDetailList(detailList, 5, formattedDouble(GIAM_TRU_GIA_CANH_BAN_THAN));
    //giam tru gia canh nguoi phu thuoc
    writeContentToDetailList(detailList, 6, formattedDouble(dependenciesDeductedAmount));
    //thu nhap chiu thue
    writeContentToDetailList(detailList, 7, formattedDouble(result > 0 ? result : 0D));

    return result;
  }

  @Override
  public Double calcFinalSalary(Double salaryAfterInsurances, Double appliedThueTNCNSalary)
  {
    return salaryAfterInsurances - appliedThueTNCNSalary;
  }

}
