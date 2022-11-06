import { TextField } from '@mui/material';
import React from 'react';
import { Controller } from 'react-hook-form';

interface FormInputTextProps {
  name: string;
  control: any;
  label?: string;
  rules?: any;
  errors?: any;
  required?: boolean;
  fieldType?: React.InputHTMLAttributes<unknown>['type'];
}

export const FormInputText = ({
  name,
  control,
  label,
  rules,
  errors,
  required,
  fieldType
}: FormInputTextProps) => {
  return (
    <Controller
      name={name}
      control={control}
      render={({ field: { onChange, value } }) => (
        <TextField
          required={required}
          onChange={onChange}
          value={value}
          label={label}
          error={errors && !!errors[name]}
          helperText={errors && errors[name] ? errors[name].message : ''}
          type={fieldType}
        />
      )}
      rules={rules}
    />
  );
};
