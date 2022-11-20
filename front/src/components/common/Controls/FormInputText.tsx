import { TextField } from '@mui/material';
import React from 'react';
import { Controller, FieldErrors, UseControllerProps } from 'react-hook-form';

interface FormInputTextProps extends UseControllerProps {
  label?: string;
  errors?: FieldErrors;
  required?: boolean;
  fieldType?: React.HTMLInputTypeAttribute;
  control: any;
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
          helperText={errors ? <>{errors[name]?.message}</> : <></>}
          type={fieldType}
        />
      )}
      rules={rules}
    />
  );
};