import { IconButton, Stack } from '@mui/material';
import { PhotoCamera } from '@mui/icons-material';
import React from 'react';
import { Controller } from 'react-hook-form';

interface Props {
  name: string;
  control: any;
  caption?: string;
  multiple?: boolean;
}

const toBase64 = (file: File) =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });

export const FormUploadImage = ({ name, control, caption, multiple }: Props) => {
  const onChange = async (e: React.ChangeEvent<HTMLInputElement>, field: any) => {
    if (!e.target.files) return;

    const base64Images: string[] = [];
    for (let i = 0; i < e.target.files.length; i++) {
      const file = e.target.files.item(i);
      if (!file) continue;
      const convertedImage = await toBase64(file);
      if (!convertedImage) continue;
      base64Images.push(convertedImage as string);
    }
    field.onChange(base64Images);
  };

  return (
    <Stack direction={'row'} spacing={1} alignItems={'center'}>
      {caption && <span>{caption}</span>}
      <IconButton color="primary" aria-label="upload picture" component="label" size={'large'}>
        <Controller
          name={name}
          control={control}
          defaultValue=""
          render={({ field }) => (
            <input
              hidden
              accept="image/*"
              type="file"
              onChange={(e) => onChange(e, field)}
              multiple={multiple}
            />
          )}
        />
        <PhotoCamera />
      </IconButton>
    </Stack>
  );
};
