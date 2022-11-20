import { IconButton, Stack } from '@mui/material';
import { PhotoCamera } from '@mui/icons-material';
import React from 'react';
import { Controller } from 'react-hook-form';

interface Props {
  name: string;
  control: any;
  multiple?: boolean;
}

export const FormUploadImage = ({ name, control, multiple }: Props) => {
  return (
    <Stack direction={'row'} spacing={1} alignItems={'center'}>
      <span>Upload photos</span>
      <IconButton color="primary" aria-label="upload picture" component="label">
        <Controller
          name={name}
          control={control}
          defaultValue=""
          render={({ field }) => (
            <input
              hidden
              accept="image/*"
              type="file"
              onChange={(e) => {
                field.onChange(e.target.files);
              }}
              multiple={multiple}
            />
          )}
        />
        <PhotoCamera />
      </IconButton>
    </Stack>
  );
};
