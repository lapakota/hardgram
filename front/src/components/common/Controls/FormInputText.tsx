import { SxProps, TextField } from '@mui/material';
import React from 'react';
import { Controller, FieldErrors, UseControllerProps } from 'react-hook-form';

interface FormInputTextProps extends UseControllerProps {
  label?: string;
  errors?: FieldErrors;
  required?: boolean;
  fieldType?: React.HTMLInputTypeAttribute;
  multiline?: boolean;
  defaultValue?: string;
  disabled?: boolean;
  sx?: SxProps;
  rows?: number;
  control: any;
}

export const FormInputText = ({
  name,
  control,
  label,
  rules,
  errors,
  required,
  multiline,
  defaultValue,
  disabled,
  fieldType,
  sx,
  rows
}: FormInputTextProps) => {
  return (
    <Controller
      name={name}
      control={control}
      render={({ field: { onChange, value } }) => (
        <TextField
          rows={rows}
          sx={sx}
          disabled={disabled}
          defaultValue={defaultValue}
          required={required}
          onChange={onChange}
          value={value}
          label={label}
          error={errors && !!errors[name]}
          helperText={errors ? <>{errors[name]?.message}</> : <></>}
          type={fieldType}
          multiline={multiline}
        />
      )}
      rules={rules}
    />
  );
};
