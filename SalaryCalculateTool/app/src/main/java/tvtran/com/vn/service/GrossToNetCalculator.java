package tvtran.com.vn.service;

import tvtran.com.vn.entity.Detail;
import tvtran.com.vn.entity.DetailGroupHeader;

import java.util.*;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/26/2017
 */
public class GrossToNetCalculator implements ICalculator
{

  //@formatter:off
  private static final double BHXH                        = 0.08;
  private static final double BHYT                        = 0.015;
  private static final double BHTN                        = 0.01;

  private static final double EIGHTY_MILIONS_RANGE        = 80000000f;
  private static final double FIFTY_TWO_MILIONS_RANGE     = 52000000f;
  private static final double THIRTY_TWO_MILIONS_RANGE    = 32000000f;
  private static final double EIGHTEEN_MILIONS_RANGE      = 18000000f;
  private static final double TEN_MILIONS_RANGE           = 10000000f;
  private static final double FIVE_MILIONS_RANGE          = 5000000f;
  private static final double GIAM_TRU_GIA_CANH_BAN_THAN  = 9000000f;
  private static final double GIA_CANH_PHU_THUOC          = 3600000f;
  //@formatter:on

  private Integer numberOfDependencies;

  private Double inputSalary;


  private Map<DetailGroupHeader, List<Detail>> detailsMap;
  private List<Detail> detailList = new ArrayList<>();
  private List<Detail> detailTNCNList = new ArrayList<>();
  private List<Detail> employerDetailList = new ArrayList<>();

  public GrossToNetCalculator(Double inputSalary, Integer numberOfDependencies)
  {
    this.numberOfDependencies = numberOfDependencies;
    this.inputSalary = inputSalary;
    this.detailsMap = new HashMap<>();
  }

  public void calculate()
  {
    double salaryAfterInsurancesSubtraction = calcInsurancesSubtraction(inputSalary);
    double salaryAfterDependenciesSubtraction = calcDependenciesSubtraction(numberOfDependencies, salaryAfterInsurancesSubtraction);
    double thueTNCNByRange = calcThueTNCNByRange(salaryAfterDependenciesSubtraction);
    double finalSalary = calcFinalSalary(salaryAfterInsurancesSubtraction, thueTNCNByRange);
    System.out.println(finalSalary);

  }

  public Double calcInsurancesSubtraction(Double salary)
  {
    final double appliedBHXH = salary * BHXH;
    final double appliedBHYT = salary * BHYT;
    final double appliedBHTN = salary * BHTN;

    detailList.add(new Detail("Bảo hiểm xã hội (8%)", formattedDouble(appliedBHXH), "", 1));
    detailList.add(new Detail("Bảo hiểm y tế (1.5%)", formattedDouble(appliedBHYT), "", 2));
    detailList.add(new Detail("Bảo hiểm thất nghiệp (1% - lương tối thiểu vùng)", formattedDouble(appliedBHTN), "", 3));

    return salary - (appliedBHXH + appliedBHYT + appliedBHTN);
  }

  @Override
  public Double calcDependenciesSubtraction(int numberOfDependencies, Double salaryAfterInsurancesSubtraction)
  {
    return salaryAfterInsurancesSubtraction - GIAM_TRU_GIA_CANH_BAN_THAN - (numberOfDependencies * GIA_CANH_PHU_THUOC);
  }

  /*
    ----- (*) Chi tiết thuế thu nhập cá nhân (VND)
    ----- Mức chịu thuế	Thuế suất	Tiền nộp
    ----- Đến   5 triệu VND	                   5%	250,000
    ----- Trên  5 triệu VND đến 10 triệu VND	10%	500,000
    ----- Trên 10 triệu VND đến 18 triệu VND	15%	458,250
    ----- Trên 18 triệu VND đến 32 triệu VND	20%	0
    ----- Trên 32 triệu VND đến 52 triệu VND	25%	0
    ----- Trên 52 triệu VND đến 80 triệu VND	30%	0
  */
  @Override
  public Double calcThueTNCNByRange(Double salaryAfterDependenciesSubtraction)
  {
    double taxTotal = 0f;
    if ((salaryAfterDependenciesSubtraction >= EIGHTY_MILIONS_RANGE) || (salaryAfterDependenciesSubtraction > FIFTY_TWO_MILIONS_RANGE && salaryAfterDependenciesSubtraction < EIGHTY_MILIONS_RANGE)) {
      taxTotal += (salaryAfterDependenciesSubtraction - FIFTY_TWO_MILIONS_RANGE) * 0.30;
      taxTotal += THIRTY_TWO_MILIONS_RANGE * 0.25;
      taxTotal += EIGHTEEN_MILIONS_RANGE * 0.20;
      taxTotal += TEN_MILIONS_RANGE * 0.15;
      taxTotal += FIVE_MILIONS_RANGE * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    if (salaryAfterDependenciesSubtraction > THIRTY_TWO_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= FIFTY_TWO_MILIONS_RANGE) {
      taxTotal += (salaryAfterDependenciesSubtraction - THIRTY_TWO_MILIONS_RANGE) * 0.25;
      taxTotal += EIGHTEEN_MILIONS_RANGE * 0.20;
      taxTotal += TEN_MILIONS_RANGE * 0.15;
      taxTotal += FIVE_MILIONS_RANGE * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    if (salaryAfterDependenciesSubtraction > EIGHTEEN_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= THIRTY_TWO_MILIONS_RANGE) {
      taxTotal += (salaryAfterDependenciesSubtraction - EIGHTEEN_MILIONS_RANGE) * 0.20;
      taxTotal += TEN_MILIONS_RANGE * 0.15;
      taxTotal += FIVE_MILIONS_RANGE * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    if (salaryAfterDependenciesSubtraction > TEN_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= EIGHTEEN_MILIONS_RANGE) {
      taxTotal += (salaryAfterDependenciesSubtraction - TEN_MILIONS_RANGE) * 0.15;
      taxTotal += FIVE_MILIONS_RANGE * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    if (salaryAfterDependenciesSubtraction > FIVE_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= TEN_MILIONS_RANGE) {
      taxTotal += (salaryAfterDependenciesSubtraction - FIVE_MILIONS_RANGE) * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    return taxTotal;
  }

  @Override
  public Double calcFinalSalary(Double salaryAfterInsurances, Double appliedThueTNCNSalary)
  {
    return salaryAfterInsurances - appliedThueTNCNSalary;
  }

  public Map<DetailGroupHeader, List<Detail>> getDetailsMap()
  {
    return detailsMap;
  }

  private String formattedDouble(Double value)
  {
    return String.format(new Locale("vi", "VN"), "%.0f", value);
  }
}
