package tvtran.com.vn.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import tvtran.com.vn.entity.City;
import tvtran.com.vn.entity.DistrictDetail;
import tvtran.com.vn.entity.DistrictGroupHeader;
import tvtran.com.vn.entity.QuestionAndAnswer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/29/2017
 */
public class MySQLiteHelper extends SQLiteOpenHelper
{
  private static final String DB_NAME = "tncn.sqlite";
  private static final int DB_VERSION = 3;

  private static final String TABLE_CITY = "city";
  private static final String TABLE_DISTRICT = "district";
  private static final String TABLE_DISTRICT_DETAIL = "districtDetail";
  private static final String TABLE_QANDA = "qanda";
  public SQLiteDatabase database = null;
  public File databaseFile;
  public String databasePath = "";
  Context mContext;

  public MySQLiteHelper(Context context)
  {
    super(context, DB_NAME, null, DB_VERSION);
    this.mContext = context;
    this.databasePath = ("data/data/" + context.getPackageName() + "/" + DB_NAME);
    this.databaseFile = new File(this.databasePath);
//    if (!this.databaseFile.exists()) {
      try {
        deployDataBase(DB_NAME, this.databasePath);
      } catch (IOException localIOException) {
        localIOException.printStackTrace();
      }
//    }
  }

  private void deployDataBase(String dbName, String dbPath)
      throws IOException
  {

    InputStream localInputStream = this.mContext.getAssets().open(dbName);
    if (isDataChanged(localInputStream, dbPath)) {
      OutputStream myOutput = new FileOutputStream(dbPath);

      //transfer bytes from the inputfile to the outputfile
      byte[] buffer = new byte[1024];
      int length;
      while ((length = localInputStream.read(buffer)) > 0) {
        myOutput.write(buffer, 0, length);
      }

      //Close the streams
      myOutput.flush();
      myOutput.close();
    }
    localInputStream.close();
  }

  boolean isDataChanged(InputStream assetLocalInputStream, String filePath) throws IOException
  {
    return !new File(filePath).exists() || assetLocalInputStream.available() != new FileInputStream(filePath).available();
  }

  @Override
  public synchronized void close()
  {

    if (database != null) {
      database.close();
    }

    super.close();

  }

  @Override
  public void onCreate(SQLiteDatabase db)
  {

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
  {

  }

  public List<City> getAllCities()
  {
    database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
    final List<City> cities = new ArrayList<>();

    final String query = "SELECT * FROM " + TABLE_CITY;

    final Cursor cursor = database.rawQuery(query, null);

    City city;

    if (cursor.moveToFirst()) {
      do {
        city = new City();
        city.setId(cursor.getInt(0));
        city.setName(cursor.getString(1));
        cities.add(city);
      } while (cursor.moveToNext());
    }
    cursor.close();
    return cities;
  }

  public List<DistrictGroupHeader> getDistrictByCity(int cityId)
  {
    database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
    final List<DistrictGroupHeader> districts = new ArrayList<>();

    final String query = "SELECT * FROM " + TABLE_DISTRICT + " WHERE cityId = " + cityId;

    final Cursor cursor = database.rawQuery(query, null);

    DistrictGroupHeader district;

    if (cursor.moveToFirst()) {
      do {
        district = new DistrictGroupHeader();
        district.setId(cursor.getInt(0));
        district.setCityId(cityId);
        district.setDisplayValue(cursor.getString(1));
        districts.add(district);
      } while (cursor.moveToNext());
    }
    cursor.close();
    return districts;

  }

  public Map<Integer, List<DistrictDetail>> getDistrictDetailsByCity(int cityId)
  {
    final Map<Integer, List<DistrictDetail>> districtDetailMap = new HashMap<>();
    database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);


    final String query = "SELECT dt.* FROM districtDetail dt JOIN district d ON dt.districtId = d.id WHERE d.cityId = " + cityId;

    final Cursor cursor = database.rawQuery(query, null);

    DistrictDetail districtDetail;

    if (cursor.moveToFirst()) {
      do {
        final List<DistrictDetail> districtDetails = new ArrayList<>();
        districtDetail = new DistrictDetail();
        districtDetail.setId(cursor.getInt(0));
        districtDetail.setAddress(cursor.getString(1));
        districtDetail.setContactInfo(cursor.getString(2));
        districtDetails.add(districtDetail);
        districtDetailMap.put(cursor.getInt(4), districtDetails);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return districtDetailMap;
  }

  public List<QuestionAndAnswer> getAllQuestionAndAnswers() {
    database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
    final List<QuestionAndAnswer> objects = new ArrayList<>();

    final String query = "SELECT * FROM " + TABLE_QANDA;

    final Cursor cursor = database.rawQuery(query, null);

    QuestionAndAnswer questionAndAnswer;

    if (cursor.moveToFirst()) {
      do {
        questionAndAnswer = new QuestionAndAnswer();
        questionAndAnswer.setId(cursor.getInt(0));
        questionAndAnswer.setShortQuestion(cursor.getString(1));
        questionAndAnswer.setQuestion(cursor.getString(2));
        questionAndAnswer.setAnswer(cursor.getString(3));
        objects.add(questionAndAnswer);
      } while (cursor.moveToNext());
    }
    cursor.close();
    return objects;
  }
}
