package fr.sharma_tools.lib.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Set;

/**
 * SharedPreferencesUtils is Utility class made to make tool more handy.
 * <p/>
 * It's using a Singleton like pattern
 * <p/>
 * Created by Louis-Gabriel ZAITI on 08/08/2014.
 * Time : 14:42
 */
public class SharedPreferencesUtils {

    private Context mContext;

    private SharedPreferencesUtils(Context _context) {
        super();
        mContext = _context;
    }

    public static SharedPreferencesUtils getInstance(Context _context) {

        return new SharedPreferencesUtils(_context);
    }

    @SuppressWarnings("unchecked")
    /**
     * Function to use string ids
     */
    public <T> void savePreference(int _sharePrefFileNameId, int _keyId, T _value) throws IllegalArgumentException {
        savePreference(mContext.getString(_sharePrefFileNameId), mContext.getString(_keyId), _value);
    }

    @SuppressWarnings("unchecked")
    /**
     * Save the preference whatever the type is.
     * <p/>
     * Becareful though, it does need valid argument to work correctly
     *
     */
    public <T> void savePreference(String _sharePrefFileName, String _key, T _value) throws IllegalArgumentException {

        if (_sharePrefFileName == null)
            throw new IllegalArgumentException("Invalid argument type. See @SharedPreferences functions accessors.");

        SharedPreferences __T_sharedPreferences = mContext.getSharedPreferences(_sharePrefFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor __T_editor = __T_sharedPreferences.edit();
        if (_value.getClass() == String.class)
            __T_editor.putString(_key, String.valueOf(_value));
        else if (_value.getClass() == int.class)
            __T_editor.putInt(_key, (Integer) _value);
        else if (_value.getClass() == float.class)
            __T_editor.putFloat(_key, (Float) _value);
        else if (_value.getClass() == boolean.class)
            __T_editor.putBoolean(_key, (Boolean) _value);
        else if (_value.getClass() == long.class)
            __T_editor.putLong(_key, (Long) _value);
        else if (_value instanceof Set<?>)
            putStringSet(_key, (Set<String>) _value, __T_editor);
        __T_editor.apply();

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private <T> void putStringSet(String _key, Set<String> _value, SharedPreferences.Editor _editor) {_editor.putStringSet(_key, _value);}

    @SuppressWarnings("unchecked")
    public <T> T getPreference(int _sharePrefFileNameId, int _keyId, T _defvalue) throws IllegalArgumentException {
        return getPreference(mContext.getString(_sharePrefFileNameId), mContext.getString(_keyId), _defvalue);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPreference(String _sharePrefFileName, String _key, T _defvalue) throws IllegalArgumentException {
        if (_sharePrefFileName == null)
            throw new IllegalArgumentException("Invalid argument type. See @SharedPreferences functions accessors.");

        SharedPreferences __T_sharedPreferences = mContext.getSharedPreferences(_sharePrefFileName, Context.MODE_PRIVATE);
        if (_defvalue.getClass() == String.class)
            return (T) _defvalue.getClass().cast(__T_sharedPreferences.getString(_key, String.valueOf(_defvalue)));
        else if (_defvalue.getClass() == int.class)
            return (T) _defvalue.getClass().cast(__T_sharedPreferences.getInt(_key, (Integer) _defvalue));
        else if (_defvalue.getClass() == float.class)
            return (T) _defvalue.getClass().cast(__T_sharedPreferences.getFloat(_key, (Float) _defvalue));
        else if (_defvalue.getClass() == boolean.class)
            return (T) _defvalue.getClass().cast(__T_sharedPreferences.getBoolean(_key, (Boolean) _defvalue));
        else if (_defvalue.getClass() == long.class)
            return (T) _defvalue.getClass().cast(__T_sharedPreferences.getLong(_key, (Long) _defvalue));
        else if (_defvalue instanceof Set<?>)
            return getStringSet(_key, (Set<String>) _defvalue, __T_sharedPreferences);
        else
            throw new IllegalArgumentException("Invalid argument type. See @SharedPreferences functions accessors.");
    }

    @SuppressWarnings("unchecked")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private <T> T getStringSet(String _key, Set<String> _defvalue, SharedPreferences _sharedPreferences) {return (T) _sharedPreferences.getStringSet(_key, _defvalue);}

}
